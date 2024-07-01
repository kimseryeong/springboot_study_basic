package com.study.basic.springboot;

import com.study.basic.springboot.web.HelloWorldController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
// - 스프링 부트 애플리케이션의 전체 컨텍스트를 로드하여 통합 테스트를 실행함
// - 실제 애플리케이션과 유사한 환경에서 테스트할 수 있음
@AutoConfigureMockMvc
// - MockMvc 객체를 자동으로 구성하여 테스트 클래스에 주입할 수 있게 함
// - MockMvc 는 웹 애플리케이션을 서버에 배포하지 않고도 테스트할 수 있도록 도와줌
class HelloWorldApplicationTests {

    @Autowired //스프링의 의존성 주입 기능을 사용하여 MockMvc 객체를 주입함
    private MockMvc mockMvc;

    @Test //JUnit 에서 이 메서드가 테스트 메서드임을 나타냄
    void helloWorldTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/helloWorld")) //mockMvc 를 사용하여 "/hello" 경로로 Get 요청 보냄
                .andExpect(status().isOk()) //HTTP 상태 코드가 200 OK 인지 확인
                .andExpect(content().string("Hello World😵‍💫")); //응답 본문이 return 값인 "Hello"와 일치하는지 확인
    }
}
