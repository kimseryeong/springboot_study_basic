package com.study.basic.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //선언된 모든 필드 get 메소드 생성
@RequiredArgsConstructor //final 이 포한된 모든 필드 생성자 생성
public class HelloWorldDto {
    private final String name;
    private final int age;
}
