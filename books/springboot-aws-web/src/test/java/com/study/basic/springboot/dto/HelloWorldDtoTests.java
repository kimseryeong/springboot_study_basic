package com.study.basic.springboot.dto;

import com.study.basic.springboot.web.dto.HelloWorldDto;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldDtoTests {
    @Test
    public void lombokTest(){
        String name = "test";
        int age = 26;

        HelloWorldDto helloWorldDto = new HelloWorldDto(name, age);

        //assertThat: assertj 라는 테스트 검증 라이브러리의 검증 메서드, 검증하고 싶은 대상을 인자로 받음
        //(JUnit 보다 더 직관적이고 다양한 기능 제공)
        assertThat(helloWorldDto.getName()).isEqualTo(name); //assertj의 동등 비교 메소드 (같을 때만 성공)
        assertThat(helloWorldDto.getAge()).isEqualTo(age);
    }
}
