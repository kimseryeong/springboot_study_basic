# Lombok 관련 Annotation

### 🏷️ @Slf4j

> logging 을 위해 Lombok 에서 제공하는 어노테이션
> <br> Logger 객체를 직접 생성하지 않고 간편하게 로그를 사용할 수 있음
>
> > `info`, `warn`, `error`, `debug`

<br>

### 🏷️ @Getter / @Setter

> 자동으로 getter/setter 메서드 생성

```
@Getter
@Setter
public class User {
    private String name;
}
```
<br>

### 🏷️ @Builder

> 빌더 패턴을 적용하여 객체를 생성

📂 참고: [Java Builder](/concepts/java-builder.md) <br>

<br>

### 🏷️ @NoArgsConstructor

> 매개변수가 없는 기본 생성자 추가
> <br> Jackson, JPA 등의 라이브러리가 객체를 인스턴스화할 때 기본 생성자가 필요한 경우
>
> > Json 역직렬화 (`ObjectMapper.readValue()`)

```
@NoArgsConstructor
public class User {
    private String name;
}
```

<br>

### 🏷️ @AllArgsConstructor

> 모든 필드를 매개변수로 받는 생성자 추가
> > Service, Repository 계층에서 빠르게 객체를 생성할 때 사용 

```
@AllArgsConstructor
public class User {
    private String name;
}
```

<br>

### 🏷️ @RequiredArgsConstructor

> `final`이 붙은 필드만 포함하는 생성자를 자동 생성

```
@RequiredArgsConstructor
public class User {
    private final String name; // name 필드만 포함하는 생성자 생성됨
}
```

<br>

### 🏷️ @ToString

> `toString()` 메서드 자동 생성

```
@ToString
public class User {
    private String name;
}
```

<br>

### 🏷️ @EqualsAndHashCode
> `equals()` 및 `hashCode()` 메서드를 자동 생성.
> > `equals()`: 객체의 내부 값을 비교할 수 있도록 해줌 
> > <br> → 같은 데이터를 가진 객체를 동일한 것으로 판단하고 싶을 때
> > <br>
> > <br> `hashCode()`: 객체의 해시값을 반환하여 컬렉션에서 빠르게 검색 가능
> > <br> → HashSet, HashMap 등의 해시 기반 컬렉션에서 중복 제거를 원할 때

```
@EqualsAndHashCode
public class User {
    private String name;
}
```