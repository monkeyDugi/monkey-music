package com.dugi.monkey.crawling.youtube;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class APIKey {

    @Getter
    @Value("${encrypted.property}")
    private static String apiKey;

//    static String apiKey() {
//        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
//
//        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
//        standardPBEStringEncryptor.setPassword("monkeyDugi");
//
//        return standardPBEStringEncryptor.decrypt("4yJbTfB0p/nIn49Qw2Hj91BWI5+MQx3W2jcUZlOWeUC8FIeEjhudM7za/V1Vk167");
//    }
}