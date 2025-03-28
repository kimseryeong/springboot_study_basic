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

| 객체지향                         | 관계형 DB                                                    |
| -------------------------------- | ------------------------------------------------------------ |
| 상속 구조, 다형성 구현이 가능      | 테이블은 상속의 개념 존재 X                                    |
| 참조를 통해 관계 표현, 방향이 있음 | 외래키를 통해 관계 표현, 방향 없음. <br> 다대다 표현 시 조인 사용 |

---

<br><br><br>

# spring-data-jpa

```
💡 Spring Data JPA 란?

    JPA 를 편하게 사용할 수 있도록 스프링에서 제공하는 라이브러리.
    엔티티 매핑부터 쿼리 작성까지 직접 처리해야 하는 작업들을 자동화,
    더 적은 코드로 데이터베이스를 다룰 수 있게 돕는 역할.
```

<br>

![spring-data-jpa](/concepts/image/spring-data-jpa.png)

<br>

| CRUD | 기존 SQL | Spring Data JPA (JpaRepository)    |
| ---- | -------- | ---------------------------------- |
| 저장 | insert   | `save(entity)`                     |
| 조회 | select   | `findById(id)`, `findAll()`        |
| 수정 | update   | `save(entity)` (변경 감지)          |
| 삭제 | delete   | `delete(entity)`, `deleteById(id)` |

<br>

### 📌 spring-data-jpa 주요 기능

- CRUD 메서드 자동 생성 (`JAPRepository` 인터페이스 상속 받으면)
- 쿼리 메서드 자동 생성
- JPQL 직접 작성할 필요 없이 `@Query` 어노테이션 사용하여 작성
- 페이징 및 정렬 시 `Pageable` 사용하여 `Limit` & `OFFSET` 적용한 페이징 쿼리 생성
- 트랜잭션 및 영속성 컨텍스트 자동 관리 `@Transactional` 없이도 가능

<br>

## ⭐ Spring Data JPA의 핵심: `Repository`

데이터베이스와 상호작용하는 DAO (Data Access Object) 역할을 하는 인터페이스

<br>

#### 🔹가장 기본 인터페이스: `Repository<T, ID>`

- 기능이 정의되어 있지 않은 최상위 인터페이스 (마커 인터페이스 역할)

```
public interface Repository<T, ID> { }
```

<br>

#### 🔹 기본적인 CRUD 제공: `CrudRepository<T, ID>`

- `save()`, `findById()`, `delete()` 같은 메서드 자동 제공

```
public interface CrudRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity);    // 저장 (Insert, Update)
    Optional<T> findById(ID id);       // ID로 조회
    Iterable<T> findAll();             // 전체 조회
    void deleteById(ID id);            // ID로 삭제
    void delete(T entity);             // 엔티티 삭제
    long count();                      // 개수 조회
}
```

<br>

#### 🔹페이징 및 정렬: `PagingAndSortingRopository<T, ID>`

- `CurdRepository` 를 확장하면서 페이징 & 정렬 기능 추가

```
public interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {
    Iterable<T> findAll(Sort sort);       // 정렬 기능 추가
    Page<T> findAll(Pageable pageable);   // 페이징 기능 추가
}
```

<br>

#### 🔹 <mark>Spring Data JPA 의 핵심: `JpaRepository<T, ID>`</mark>

- CRUD + Paging + 배치 작업 + JPQL 확장 기능 제공
- 주로 `JpaRepository` 를 상속받아서 사용

```
public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID> {
    List<T> findAll();                                    // 전체 조회
    void flush();                                         // 변경 사항 즉시 반영
    <S extends T> List<S> saveAll(Iterable<S> entities);  // 여러 개 저장
    void deleteInBatch(Iterable<T> entities);             // 여러 개 삭제
}
```

```
//Example

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
    //userId 로 todolist 조회하기
    List<TodoEntity> findByUserId(String userId);
}
```
