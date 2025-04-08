# 📌 DTO와 Entity 분리

```
JPA 는 데이터베이스와 객체를 자동으로 매핑해주는 기술, 이 특징 때문에 DTO와 Entity를 분리하는 것이 중요
```

|||
|---|---|
| DTO (Data Transfer Object)| Client 와 데이터를 주고받을 때 사용하는 객체 |
| Entity| DB와 연결되는 클래스 | 


## ☑️ 분리하는 이유?

### 1️⃣ Entity 는 데이터베이스와 연결되어 있음 (Entity는 JPA가 관리하는 객체)

<br> → 트랜잭션이 종료될 때 변경 사항이 자동 반영됨 (변경 감지에 의함)

Entity를 API 응답으로 직접 보내면 클라이언트가 데이터를 변경할 수도 있고, 원하지 않는 변경이 DB에 반영될 위험이 있음

<br>

#### ⚠️ Entity를 직접 반환한 나쁜 예시

```
@GetMapping("/todos")
public List<TodoEntity> getTodos() {
    return todoRepository.findAll();
}
```

<br>

#### 🗝️ DTO를 사용한 좋은 예시

```
@GetMapping("/todos")
public List<TodoDTO> getTodos() {
    List<TodoEntity> entities = todoRepository.findAll();

    return entities.stream().map(TodoDTO::new).collect(Collectors.toList());
}
```

<br>

### 2️⃣ Lazy Loading 문제 (불필요한 쿼리 방지)

→ JPA 에서 연관된 엔티티를 LAZY 로딩할 수 있음

Entity 그대로 반환 시 필요하지 않은 데이터까지 가져오면서 성능 저하
<br> DTO 사용하여 필요한 필드만 선택적으로 가져와 성능 최적화

#### ⚠️ Entity를 직접 반환한 나쁜 예시

```
public List<TodoEntity> getTodos() {
    return todoRepository.findAll();
    // Lazy Loading으로 인해 N+1 문제 발생 가능
}
```

#### 🗝️ DTO를 사용한 좋은 예시

```
@Query("SELECT new com.example.TodoDTO(t.id, t.title) FROM TodoEntity t WHERE t.uuid = :uuid")
List<TodoDTO> findTodosByUserId(@Param("uuid") String uuid);
```

<br>

### 3️⃣ Entity는 DB와 1:1 구조, DTO는 API에 맞게 가공

Entity는 데이터베이스 테이블과 직접 매핑 → 유연성 ↓
<br> DTO는 API의 요구사항에 맞춰 가공 → 유연성 ↑

<br>
<br>

## ☑️ 분리 후 DTO <-> Entity 변환 필요

데이터를 Entity에서 DTO로 변환하거나, DTO에서 Entity로 변환하는 작업이 필요
이때 사용하는 방식 → 생성자, 정적 팩토리 메서드, @Builder

### 1️⃣ Entity → DTO 로 변환

> DB 에서 꺼낸 데이터 클라이언트에게 전달하기 위해

```
@Getter
public class TodoDto {
    private Long id;
    private String title;
    private boolean done;

    //방법1. Entity를 받아서 DTO로 변환하는 생성자
    public TodoDto(TodoEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }

    //방법2. Builder 사용
    @Builder
    public TodoDto(Long id, String title, boolean done) {
        this.id = id;
        this.title = title;
        this.done = done;
    }
}

```

위 코드를 토대로 서비스 계층에서 사용하는 방법
```
//방법1. 생성자 방식
TodoDto dto = new TodoDto(todoEntity); 

//방법2. Builder 방식
TodoDto dto = TodoDto.builder()
                .id(todoEntity.getId())
                .title(todoEntity.getTitle())
                .done(todoEntity.isDone())
                .build();

```

### 2️⃣ DTO → Entity 로 변환

> 사용자가 입력한 데이터를 DTO로 받아서 DB에 저장하기 위해

```
@Entity
@Getter
@NoArgsConstructor
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private boolean done;

    //방법1. DTO 기반 생성자
    public TodoEntity(TodoDto dto) {
        this.title = dto.getTitle();
        this.done = dto.isDone();
    }

    //방법2. Builder 사용
    @Builder
    public TodoEntity(Long id, String title, boolean done) {
        this.id = id;
        this.title = title;
        this.done = done;
    }
}
```

위 코드를 토대로 서비스 계층에서 사용하는 방법

```
//방법1. 생성자 방식
TodoEntity entity = new TodoEntity(todoDto); 

//방법2. Builder 방식
TodoEntity entity = TodoEntity.builder()
                    .title(todoDto.getTitle())
                    .done(todoDto.isDone())
                    .build();
```

<br>

# 📌 DTO 가공 -> RequestDTO / ResponseDTO

|||
|---|---|
| `Client → Server` | 요청 (사용자가 데이터 입력)|
| `Server → Client` | 응답 (서버가 데이터 조회 후 전달)|

<br>
각 경우에 따라 필요한 필드 및 검증 조건이 다를 수 있으므로 분리하여 가공하는 것이 좋음

### Request DTO
사용자가 직접 JSON 으로 보낸 데이터가 자동으로 바인딩 되므로 변환 필요 X
```
@Getter
@NoArgsConstructor
public class TodoRequestDto {

    @NotBlank
    private String title;

    private boolean done;

    @Builder
    public TodoRequestDto(String title, boolean done) {
        this.title = title;
        this.done = done;
    }
}
```

### Response DTO
Entity → ResponseDto 변환 필요 O

```
@Getter
@NoArgsConstructor
public class TodoResponseDto {

    private Long id;
    private String title;
    private boolean done;

    @Builder
    public TodoResponseDto(Long id, String title, boolean done) {
        this.id = id;
        this.title = title;
        this.done = done;
    }

    // Entity → ResponseDto 변환 필요!
    public static TodoResponseDto fromEntity(TodoEntity entity) {
        return TodoResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .done(entity.isDone())
                .build();
    }
}
```