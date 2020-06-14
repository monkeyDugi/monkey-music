package com.dugi.monkey.crawling.youtube;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class APIKeyTest {

    @Test
    public void youtube_API_KEY는_NULL이_아니다() {
        assertThat(APIKey.getApiKey()).isNotNull();
    }
}