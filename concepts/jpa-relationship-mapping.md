# JPA Relationship Mapping (JPA 연관관계 매핑)

```
💡 JPA 연관관계 매핑
객체의 참조와 테이블의 외래 키를 매핑하는 것.
관계형 DB와 다르게 JPA 는 연관 관계를 설정하여 연관관계의 방향 및 주인을 고려해야 함
```

### 📌 연관관계 방향 (Direction)
**단방향**
<br>두 Entity가 관계를 맺을 때 한쪽의 Entity만 참조하고 있는 것을 의미

**양방향**
<br>두 Entity가 관계를 맺을 때 양쪽이 서로 참조하고 있는 것을 의미

<br>

### 📌 연관관계 다중성 (Multiplicity)
관계에 있는 두 Entity가 다음 중 하나의 관계를 갖는 것을 의미

|||
|--|--|
|`@OneToOne` |1:1|
|`@OneToMany` |1:N|
|`@ManyToOne` |N:1|
|`@ManyToMany` |N:N|
|||

<br>

### 📌 연관관계 주인 (Owner)
- Entity 가 서로 양방향 관계일 경우 연관 관계의 주인이 필요
- 외래키를 저장, 수정, 삭제하는 등의 관리 권한을 가짐
- 주인이 아닌 Entity는 조회만 가능하며, mappedBy 속성을 사용하여 표현
- 일반적으로 외래키가 있는 Entity 를 주인으로 설정

<br>

### 📑 예시

||||
|--|--|--|
| 1 |:| N|
| Users (Owner) || Todos|
||||

#### ✔️ Users
```
@Entity
@Table
public class Users extends BaseTimeEntity{

	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private UUID uuid;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private String password;
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Todos> todoList = new ArrayList<>();

}
```

#### ✔️ Todos
```
@Entity
@Table
public class Todos extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private boolean completed = false;

	
	@ManyToOne
	@JoinColumn(name="user_uuid")
	private Users users;

	
}
```