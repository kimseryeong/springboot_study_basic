package com.study.basic.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//보통 MyBatis 등에서 Dao 라고 불리는 DB Layer 접근자. JPA 에선 Repository 라고 부름.
//인터페이스 생성 후 JpaRepository<Entitiy Class name, PK type> 을 extends 해주면 CRUD 메소드 자동으로 생성됨
//Entity 클래스와 함께 위치해야 함
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
