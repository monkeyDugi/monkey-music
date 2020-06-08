package com.dugi.monkey.crawling.youtube;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum  APIKey {

    REAL_API_KEY("AIzaSyC8OSK4rWi-la7PbAZk3XwE2TIdIf3VY3w"),
    TEST_API_KEY("AIzaSyAB-DoSDnW8aWvxv671mAd2bCvfHMjkeoE");

    private final String apiKey;
//    public static String apiKey() {
//        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
//
//        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
//        standardPBEStringEncryptor.setPassword("monkeyDugi");
//
//        return standardPBEStringEncryptor.decrypt("goUwYcn43JyzLCVpX9HB81T6QtSQ2FG4sNudAjhm371RDPQGUJvdbkxOvkn5SpEp");
//    }
}
