package com.volonter.Bibilioteka.security.encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class Decryptor {
    @Autowired
    private EncryptionProperties properties;

    public String decrypt(String value){
        try{
            SecretKeySpec keySpec = new SecretKeySpec(properties.getKey().getBytes(StandardCharsets.UTF_8), properties.getAlg());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,keySpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(value)),StandardCharsets.UTF_8);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
