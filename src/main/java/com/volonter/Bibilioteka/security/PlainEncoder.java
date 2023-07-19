package com.volonter.Bibilioteka.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainEncoder implements PasswordEncoder {

    private static PlainEncoder instance;

    private PlainEncoder(){}

    public static PlainEncoder getInstance() {
        if(instance == null){
            instance = new PlainEncoder();
        }
        return instance;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}
