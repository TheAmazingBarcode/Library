package com.volonter.Bibilioteka.security.encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class Encryptor {
    @Autowired
    private EncryptionProperties properties;

    public String encrypt(String value){
        try{
            SecretKeySpec keySpec = new SecretKeySpec(properties.getKey().getBytes(StandardCharsets.UTF_8), properties.getAlg());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,keySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes(StandardCharsets.UTF_8)));
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
