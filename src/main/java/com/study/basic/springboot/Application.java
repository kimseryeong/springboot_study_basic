package com.study.basic.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //스프링부트 자동 설정, 스프링 Bean 읽기와 생성 자동으로 설정
public class Application { //프로젝트의 메인 클래스 (프로젝트 최상단에 위치해야 함)
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //내장 WAS 실행
    }
}
