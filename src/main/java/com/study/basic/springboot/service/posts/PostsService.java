package com.study.basic.springboot.service.posts;

import com.study.basic.springboot.domain.posts.PostsRepository;
import com.study.basic.springboot.web.dto.PostsSaveRequestDto;
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
}
