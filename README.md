# spring boot 공부

***

#### 🏷️ 깃 커밋 컨벤션
|태그|설명|
|------|--------------------------------|
|feat|새로운 개념/기능 추가|
|fix|버그 수정|
|refactor|리팩토링|
|chore|기타 변경사항 (설정 변경, 환경 설정 관련)|
|docs|문서 추가, 수정, 삭제|
|test|테스트 코드 추가/수정|
|init|초기 생성|

***

## 💡 테스트 코드

#### [테스트 코드 작성 이유]
 - 코드 기능이 예상대로 동작하는지 확인할 수 있고 코드 작성 초기 단계에서 버그를 발견할 수 있음
 - 코드 리팩토링 또는 새로운 기능 추가 시 기존 기능이 제대로 잘 동작하는지 확인할 수 있음

<br>

**JUnit**
- 자바에서 단위 테스트를 지원해주는 프레임워크
- TDD 지원
- Assert(검증)를 이용하여 결과의 기댓값과 실제값 비교
- @Test 어노테이션 마다 독립적으로 테스트 진행됨

<br>

**AssertJ**
- Fluent Assertion 라이브러리 (JUnit 과 함께 사용됨)
- 자바에서 가독성 높고 강력한 검증 제공 
- 체이닝 메서드 지원(체이닝 메서드: 메서드 호출이 연속적으로 이어지는 패턴)

***

## 💡 JPA

**정의**
- 자바에서 사용하는 ORM(Object Relational Mapping) 기술 표준 
- 자바 애플리케이션과 JDBC 사이에서 동작하며 자바 인터페이스로 정의되어 있음 
- SQL 쿼리문을 직접 작성할 필요 없이 메서드로 CRUD 접근 가능

**ORM**
- 객체와 관계형 테이터베이스의 데이터를 매핑하는 기술
- 객체와 테이블을 매핑하여 패러다임 불일치 문제 해결

***

## 💡 DDD(Domain-Driven-Design)
- 도메인 모델 자체가 비즈니스 로직을 포함하고 있는 설계
- 비즈니스 로직이 도메인 모델에 포함되어 있어 개념과 규칙을 명확히 표현할 수 있음 
- <a href="https://velog.io/@dnflekf2748/DDDDomain-Driven-Design">DDD 개념 참고 블로그</a>

## 💡 폴더 구조 및 역할
📂 domain/  <br>
├── EntityClass.java <br>
├── RepositoryInterface.java <br>
📂 service/  <br>
├── ServiceClass.java <br>
📂 web/  <br>
├── ApiController.java <br>
├── 📂 dto/ <br>
├── ├── Dto.java <br>

DTO 
- 계층 간 데이터 전송을 위해 사용되는 객체
- 데이터 표현과 전송에 집중하며 비즈니스 로직 포함 X 

Entity Class
- 데이터베이스 테이블과 매핑되는 객체
- JPA 와 같은 ORM 프레임워크에서 주로 사용됨 

Repository Interface
- 데이터베이스와 CRUD 작업을 추상화
- JPA 레포지토리 상속받아 자동으로 CRUD 수행

Service Class
- 보통 비즈니스 로직을 포함하며 트랜잭션을 관리함 
- 레포지토리와 상호작용하고 DTO를 통해 데이터 처리 

Controller Class
- HTTP 요청을 처리하고 서비스 계층과 통신하여 필요한 작업 수행함 
