# JPA (Java Persistence API)

```
💡 JPA 란?

- Java *ORM(Object-Relational Mapping) 기술 표준
- Java 애플리케이션에서 관계형 DB를 사용하는 방식을 정의한 인터페이스

```

### 📌 JPA 주요 기능

- 객체와 관계형 데이터베이스 매핑 (ORM)
- 영속성 컨텍스트 관리 (엔티티 객체의 생명주기 관리)
- JPQL (Java Persistence Query Language) 지원
- 트랜잭션 관리 지원

> JPA 는 단순한 표준 인터페이스이므로 실제 구현체가 필요!
>
> > 대표적인 구현체: `Hibernate`, `EclipseLink`, `OpenJPA ` 등

<br>

![jpa](/concepts/image/jpa.png)

<br>

### 📌 JPA 사용하는 이유

> 반복적인 CRUD SQL을 처리해줌으로써 간단한 메서드로 처리 가능

| CRUD | 기존 SQL | JPA(EntityManager)                                     |
| ---- | -------- | ------------------------------------------------------ |
| 저장 | insert   | `entityManager.persist()`                              |
| 조회 | select   | `entityManager.find()`                                 |
| 수정 | update   | `entityManager.setValueName("changeName")` (변경 감지) |
| 삭제 | delete   | `entityManager.remove()`                               |

<br>

> 객체 중심으로 개발할 수 있어 생산성 및 유지보수성 ↑

→ 필드가 변경되더라도 매핑 정보만 잘 연결하면 SQL문은 JPA가 처리

<br>

> 객체와 테이블을 매핑하여 패러다임의 불일치 해결

| 객체지향                           | 관계형 DB                                                         |
| ---------------------------------- | ----------------------------------------------------------------- |
| 상속 구조, 다형성 구현이 가능      | 테이블은 상속의 개념 존재 X                                       |
| 참조를 통해 관계 표현, 방향이 있음 | 외래키를 통해 관계 표현, 방향 없음. <br> 다대다 표현 시 조인 사용 |

---
