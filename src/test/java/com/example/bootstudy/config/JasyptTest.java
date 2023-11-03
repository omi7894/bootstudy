package com.example.bootstudy.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;

@SpringBootTest
public class JasyptTest {

    @Test
    void encryptTest(){
        String id = "root";
        String password = "1234";

        System.out.println(jasyptEncoding(id));
        System.out.println(jasyptEncoding(password));

    }

    public String jasyptEncoding(String value){
        String key = "around_hub_studio";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }
}
