package com.dugi.monkey.crawling.youtube;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;

@Component
public class APIKey {

    public static String apiKey() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();

        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        standardPBEStringEncryptor.setPassword("monkeyDugi");

        return standardPBEStringEncryptor.decrypt("4yJbTfB0p/nIn49Qw2Hj91BWI5+MQx3W2jcUZlOWeUC8FIeEjhudM7za/V1Vk167");
    }
}
