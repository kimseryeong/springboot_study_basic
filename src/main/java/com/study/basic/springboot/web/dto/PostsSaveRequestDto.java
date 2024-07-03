package com.study.basic.springboot.web.dto;

import com.study.basic.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor //파라미터가 없는 기본 생성자 자동으로 생성(JPA 에서 주로 Entity 생성할 때 사용됨)
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;


    @Builder //빌드 패턴 구현 어노테이션. 가독성을 높이고 선택적으로 값을 설정할 수 있음 (여러 필드를 가진 객체 생성 시 유용)
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
