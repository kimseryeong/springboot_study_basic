# @Annotation

```
💡 annotation 란?
- 특별한 의미, 기능을 수행하도록 하는 기술
- 프로그램에게 추가적인 정보를 제공해주는 메타데이터
- 컴파일러에 코드 작성 문법 에러를 체크하도록 정보 제공
- 빌드나 배치 시 코드를 자동으로 생성할 수 있도록 정보 제공
- 실행 시 특정 기능을 실행하도록 정보 제공

```

### @ComponentScan

> `@Service`, `@Repository`, `@Controller`, `@Configuration`이 붙은 클래스 Bean 들을 찾아서 Context에 bean 등록

<br>

### @SpringBootApplication

> `@Configuration`, `@EnableAutoConfiguration`, `@ComponentScan` 을 포함
> <br> 애플리케이션 설정을 자동으로 처리

<br>

### @RestController

> RESTful 구현하기 위해 컨트롤러 클래스에서 사용
> <br> HTTP 요청을 처리하고 그 응답을 JSON 형식으로 반환
> <br> `@Controller` + `@ResponseBody` (@ResponseBody를 모든 메서드에서 적용)
> <br> View 가 필요없는 API만 지원하는 서비스에서 사용

<br>

### @GetMapping, @PostMapping, @PutMapping, @DeleteMapping

> HTTP 메서드 (GET, POST, PUT, DELETE)와 요청 경로 매핑
> <br> @RequestMapping 보다 직관적

<br>

### @Autowired

> 의존성 주입을 위한 어노테이션으로 Spring 컨테이너에 관리되는 빈을 자동으로 주입받을 수 있음.
> <br> Type 에 따라 알아서 Bean 주입

<br>

### @Value

> 외부 설정 파일(application.properties)에서 값을 주입받을 때 사용.
> <br> 프로퍼티 값을 필드에 바인딩할 수 있음

<br>

### @Component, @Service, @Repository

> Spring 에서 관리하는 빈 생성 시 사용되는 어노테이션
> <br> `@Component`: 일반적인 빈 정의 (개발자가 직접 작성한 Class를 Bean으로 등록)
> <br> `@Service`: 서비스 계층, 비즈니스 로직 수행하는 Class라고 명시
> <br> `@Repository`: 데이터 접근 계층 정의 (DAO)

```
@Component(value="mystudent") //value를 통해 Bean 이름 지정
public class Student {
    public Student() {
        System.out.println("hi");
    }
}
```

<br>

### @Configuration

> Spring Bean 을 정의하는 설정 클래스를 의미
> <br> Bean 을 정의하고 `@Bean` 어노테이션을 통해 객체 관리 가능

<br>

### @Bean

> `@Configuration` 클래스 내에서 사용되어 특정 객체를 Spring Bean 으로 등록
> 개발자가 직접 제어가 불가능한 외부 라이브러리 등을 Bean 으로 만들 때 사용

```
@Configuration
public class ApplicationConfig{

    @Bean
    public ArrayList<String> array(){
        return new ArrayList<String>();
    }
}
```

<br>

### @EnableAutoConfiguration

> Spring Boot의 자동 설정 기능을 활성화하는 어노테이션
> <br> Spring Application Context를 만들 때 자동으로 설정하는 기능
> <br> classpath의 내용에 기반해서 자동으로 생성

<br>

### @Required

> setter method 에 적용하면 Bean 생성 시 필수 프로퍼티 임을 알림
> <br> 꼭 필요한 속성들을 정의할 때 사용

<br>

### @Slf4j

> logging 을 위해 Lombok 에서 제공하는 어노테이션
> <br> Logger 객체를 직접 생성하지 않고 간편하게 로그를 사용할 수 있음
>
> > `info`, `warn`, `error`, `debug`
