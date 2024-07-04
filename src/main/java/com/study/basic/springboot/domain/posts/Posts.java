package com.study.basic.springboot.domain.posts;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity //JPA의 어노테이션 - 객체와 테이블 매핑 역할
public class Posts { //클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블이름 매칭
    @Id //해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue - PK 생성 규칙 (스프링부트 2.0 에서는 GenerationType.IDENTITY 옵션을 추가하여 auto_increment)
    private Long id;

    //@Column - 테이블의 컬럼을 나타내며 생략 가능. 보통 변경이 필요한 옵션이 있을 때 정의하여 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
