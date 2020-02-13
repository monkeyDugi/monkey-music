package com.dugi.monkey.music.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 1. 테스트 진행 시 JUnit에 내장된 실행자 외에 다른 실행자를 실행한다
                             // 2. 여기서는 SpringRunner라는 스프링 실행자를 사용
                             // 3. 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
@WebMvcTest // 1. 여러가지 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중
            // 2. 선언 시 @Controller, @ControllerAdvice 등 사용 가능
            // 3. 단, @Service, @Component, @Repository 등은 사용 불가
            // 4. 여기서는 Controller만 사용하기 때문에 사용
public class MusicControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void dailyList가_리턴된다() throws Exception {
        String test = "TEST";

        mockMvc.perform(get("/music/chart/daily"))
                .andExpect(status().isOk())
                .andExpect(content().string(test));
    }
}
