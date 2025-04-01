# JPA 관련 Annotation

### 🏷️ @Entity

> JPA 가 관리하는 객체(엔티티)임을 나타냄
> > 데이터베이스 테이블과 매핑됨

<br>

### 🏷️ @Table

> @Entity 와 함께 사용되며 해당 엔티티가 매핑될 테이블 정보를 설정
> > `name` 속성을 사용하여 테이블명을 지정

```
@Entity
@Table(name = "users")  // 테이블명을 'users'로 지정
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
```
<br>

### @Id

> Entity 의 기본 키(Primary key) 지정

<br>

### 🏷️ @GeneratedValue

> 기본 키 값을 자동 생성할 때 사용
> <br> `strategy` 속성 사용하여 키 생성 

|strategy|방법|
|--|--|
|IDENTITY|기본 키 생성을 DB가 자동으로 수행 (MySQL, PostgresSQL)|
|SEQUENCE|별도의 시퀀스를 생성해서 사용 (Oracle, PostgreSQL)|
|TABLE|키 값을 저장하는 별도 테이블 사용|
|AUTO|JPA 가 자동으로 전략 선택|

<br>

### 🏷️ @Column

> 데이터베이스 테이블의 컬럼과 매핑
> <br> 컬럼의 속성을 지정할 수 있음

```
@Column(name = "user_name", nullable = false, length = 50)
private String name;
```

<br>

### 🏷️ @OneToOne / @OneToMany / @ManyToOne / @ManyToMany

> 엔티티 간의 관계 설정

```
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
```

<br>

### 🏷️ @JoinColumn

> 외래 키(Foreign Key) 지정할 때 사용

```
@OneToOne
@JoinColumn(name = "address_id")  // User 테이블의 address_id 컬럼이 외래 키가 됨.
private Address address;
```

<br>

### 🏷️ @MappedSuperclass

> 공통 매핑 정보를 가진 부모 클래스를 정의할 때 사용
> <br> @Entity가 붙은 클래스들이 이 클래스를 상속받으면 해당 필드가 테이블에 포함됨

```
@MappedSuperclass
public abstract class BaseEntity {
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
```

### 🏷️ @Embeddable / @Embedded

> @Embeddable → 재사용 가능한 값 타입 클래스를 정의할 때 사용.

> @Embedded → @Embeddable 클래스를 포함할 때 사용.

```
@Embeddable
public class Address {
    private String city;
    private String street;
}

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;
}
```