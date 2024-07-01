package com.study.basic.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//- 컨트롤러를 JSON으로 반환하는 컨트롤러로 만들어줌 (REST ful 웹 서비스 컨트롤러임을 나타냄)
//- @Controller + @ResponseBody를 결합한 것
public class HelloWorldController {

    @GetMapping("/helloWorld") //Http Get 요청을 받을 수 있는 API
    public String hello(){
        return "Hello World😵‍💫";
    }

}
