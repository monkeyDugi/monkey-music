package com.dugi.monkey.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class jasyptConfigTest {

    @Test
    public void test() throws Exception {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();

        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        standardPBEStringEncryptor.setPassword("monkeyDugi");
//        standardPBEStringEncryptor.setStringOutputType("base64");

//        String enc = standardPBEStringEncryptor.encrypt("이병덕");
        String des = standardPBEStringEncryptor.decrypt("ugw17ozluAaUYlnuPPf0kWUQlVhO5qqV");
//        System.out.println("enc = " + enc);
        System.out.println("des = " + des);
    }
}