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
| 수정 | update   | `save(entity)` (변경 감지)         |
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

### 🔹가장 기본 인터페이스: `Repository<T, ID>`

- 기능이 정의되어 있지 않은 최상위 인터페이스 (마커 인터페이스 역할)

```
public interface Repository<T, ID> { }
```

<br>

### 🔹 기본적인 CRUD 제공: `CrudRepository<T, ID>`

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

### 🔹페이징 및 정렬: `PagingAndSortingRopository<T, ID>`

- `CurdRepository` 를 확장하면서 페이징 & 정렬 기능 추가

```
public interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {
    Iterable<T> findAll(Sort sort);       // 정렬 기능 추가
    Page<T> findAll(Pageable pageable);   // 페이징 기능 추가
}
```

<br>

### 🔹 <mark>Spring Data JPA 의 핵심</mark>: `JpaRepository<T, ID>`

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

<br>

#### 🔎 Example

```

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
    //userId 로 todolist 조회하기
    List<TodoEntity> findByUserId(String userId);
}
```
