package com.jojoldu.book.springboot.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@RunWith - 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행
// 여기서는 SpringRunner라는 스프링 실행자를 사용
// 즉, 스프링부트테스트와 JUnit 사이에 연결자 역할을 한다
//JUnit5 에서는 필요 없음 Extension 모델을 도입.... JUnit5로 가보자
//JUnit5 사용할 경우 public이 아니어도 된다.

//WebMvcTest - Web에 집중할 수 잇는 어노테이션
//선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음
@WebMvcTest(controllers =  HelloController.class)
class HelloControllerTest {

    //Spring이 관리하는 Bean을 주입 받는다
    @Autowired
    private MockMvc mvc;
    // 웹 api를 테스트할때 사용
    //스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음

    @Test
    @DisplayName("GET /hello 요청 시 'hello' 반환") // 테스트 패널에 읽기 좋은 이름으로 변환
    void hello가_리턴된다() throws Exception{
        String hello = "hello";
        
        
        // MockMvc를 통해 /hello 주소로 HTTP GET 요청
        // 체이닝이 지원되어 여러 검증 기능 이어서 선언 가능
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증 status를 검증 OK는 200인지 검증
                .andExpect(content().string(hello));
                // 응답 본문의 내용을 검증 hello가 맞는지 검증
        
    }

    @Test
    @DisplayName(" GET /hello/dto 요청 시 HelloResponseDto 반환")
    void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform( get("/hello/dto")
                        .param("name",name)
                        .param("amount", String.valueOf(amount)) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

        //param - API 테스트할 때 사용될 요청 파라미터를 설정
        // 단 값은 String 값만 허용
        // 그래서 숫자/날짜 데이터도 등록할 때는 문자열로 변경해야만 한다.
        //jsonPath - JSON 응답값을 필드별로 검증할 수 있는 메소드
        // $값을 기준으로 필드명을 명시한다
        // 여기선느 name과 amount를 검증하니 저렇게



    }
}
