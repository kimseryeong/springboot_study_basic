# 🛠 [GenericGenerator-annotation-error]

### 🤬 error

```
'org.hibernate.annotations.GenericGenerator' is deprecated since version 6.5
```

<br>

### 🔍 cause

uuid 를 생성해줄 때 오래된 방식의 어노테이션 사용 시 발생한 오류

```
//내 코드

@Id
@GeneratedValue(generator="system-uuid")
@GenericGenerator(name="system-uuid", strategy = "uuid")
private String uuid;
...
```

<br>

### ✅ solution

1️⃣ `@GeneratedValue(strategy = GenerationType.AUTO)` 를 사용

> JPA 표준 (Java Persistence API) <br>
> Hibernate가 UUID.randomUUID() 또는 DB 기능 사용 <br>
> DB에 따라 다름 (특정 DB의 UUID 기능에 의존 가능) <br>
> JPA 표준이므로 다른 JPA 구현체에서도 사용 가능

```
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private UUID id;
```

<br>

2️⃣ `@UuidGenerator` 를 사용

> Hibernate 전용 기능
> Hibernate가 UUID.randomUUID() 사용
> Hibernate에서 자동 생성 <br>
> Hibernate 6.0 이상에서만 사용 가능

```
@Id
@UuidGenerator
private UUID id;
```
