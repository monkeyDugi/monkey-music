package com.dugi.monkey.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class mustacheControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 일간차트_페이지_로딩() {
        // when
        String body = this.restTemplate.getForObject("/charts/daily/", String.class);

        // then
        assertThat(body).contains("순위");
    }
}
