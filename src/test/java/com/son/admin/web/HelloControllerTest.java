package com.son.admin.web;

/*
    RunWith -> 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
               SpringRunner이라는 스프링 실행자 사용
               스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함
    WebMvcTest -> Web에 집중할 수 있는 어노테이션
    Autowired -> 스프링이 관리하는 빈을 주입 받음
    private MockMvc mvc -> 웹 API를 테스트할 때 사용
                           스프링 MVC 테스트의 시작점
                           HTTP GET, POST 등에 대한 API 테스트를 할 수 있음
    mvc.perform(get("/hello")) -> MockMvc를 통해 /hello 주소로 HTTP GET 요청
    .andExpect(status().isOk()) -> mvc.perfom 결과 검증
                                   HTTP Header의 Status를 검증
    .andExpect(content().string(hello)) -> mvc.perfom 결과 검증
    param -> API 테스트할 때 사용될 요청 파라미터 설정
    jsonPath -> JSON 응답값을 필드별로 검증할 수 있는 메소드
                $를 기준으로 필드명 명시

 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
