package com.dugi.monkey.crawling.youtube;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APIKeyTest {

    @Autowired
    private APIKey apiKey;

    @Test
    public void youtube_API_KEY는_NULL이_아니다() {
        assertThat(apiKey.getYoutube()).isNotNull();
        System.out.println("dugi : " + apiKey.getYoutube());
    }
}