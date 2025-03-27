package com.study.basic.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @AfterEach //JUnit 에서 단위 테스트가 끝날 때마다 수행되는 메소드 지정하는 어노테이션. 테스트 간 데이터 침범을 막기 위한 용도로 사용됨
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void savePosts(){
        //given
        String title = "게시글 타이틀";
        String content = "게시글 본문";

        //id 값이 있다면 update, 없다면 insert 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("test@gmail.com")
                .build()
        );

        //when
        //posts 테이블에 있는 모든 데이터 조회
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
