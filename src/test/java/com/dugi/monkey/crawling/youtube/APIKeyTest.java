package com.dugi.monkey.crawling.youtube;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = APIKey.class)
public class APIKeyTest {

    @Autowired
    private APIKey apiKey;

    @Test
    public void youtube_API_KEY는_NULL이_아니다() {
//        assertThat(apiKey.getYoutube()).isEqualTo("AIzaSyC8OSK4rWi-la7PbAZk3XwE2TIdIf3VY3w");
        assertThat(apiKey.getYoutube()).isEqualTo("${api.youtube}");
        log.info("dugi : " + apiKey.getYoutube());
    }
}