package com.dugi.monkey.crawling.youtube;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum APIKey {

    REAL_API_KEY("AIzaSyC8OSK4rWi-la7PbAZk3XwE2TIdIf3VY3w"),
    TEST_API_KEY("AIzaSyAB-DoSDnW8aWvxv671mAd2bCvfHMjkeoE");

    private final String apiKey;
}
