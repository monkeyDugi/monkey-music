package com.dugi.monkey.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;

public class jasyptConfigTest {

    @Test
    public void test() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();

        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        standardPBEStringEncryptor.setPassword("monkeyDugi");

        String des = standardPBEStringEncryptor.decrypt("goUwYcn43JyzLCVpX9HB81T6QtSQ2FG4sNudAjhm371RDPQGUJvdbkxOvkn5SpEp");
        System.out.println("des = " + des);
    }
}