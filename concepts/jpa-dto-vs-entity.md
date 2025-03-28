# 📌 DTO와 Entity 분리

```
JPA 는 데이터베이스와 객체를 자동으로 매핑해주는 기술, 이 특징 때문에 DTO와 Entity를 분리하는 것이 중요
```

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
