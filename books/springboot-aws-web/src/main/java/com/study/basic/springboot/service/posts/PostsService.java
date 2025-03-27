package com.study.basic.springboot.service.posts;

import com.study.basic.springboot.domain.posts.Posts;
import com.study.basic.springboot.domain.posts.PostsRepository;
import com.study.basic.springboot.web.dto.PostsResponseDto;
import com.study.basic.springboot.web.dto.PostsSaveRequestDto;
import com.study.basic.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor //@Autowired 대신 생성자로 Bean 객체 주입
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto reqDto){
        return postsRepository.save(reqDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없음 id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없음 id=" + id));

        return new PostsResponseDto(entity);
    }
}
