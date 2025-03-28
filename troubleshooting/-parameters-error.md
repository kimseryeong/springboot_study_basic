# 🛠 [@PathVariable 사용 시 -parameters 오류]

### 🤬 error

```
java.lang.IllegalArgumentException: Name for argument of type [java.lang.String] not specified, and parameter name information not available via reflection.Ensure that the compiler uses the ‘-parameters’ flag.
```

<br>

### 🔎 cause

```
spring boot 3.2 부터는 매개변수 이름을 인식하지 못하는 문제가 있다고 함.

특히 `@RequestParam` `@PathVariable` `@Autowired` `@ConfigurationProperties` 등의 어노테이션 사용 시 발생
```

<br>

### ✅ solution

name을 명시해주기

`@RequestParam(required=false) String email`

→ `@RequestParam(name="email", required=false) String email`

or `@PathVariable(”email”)`
