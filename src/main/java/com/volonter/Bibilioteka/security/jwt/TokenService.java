package com.volonter.Bibilioteka.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
public class TokenService {

    @Autowired
    private TokenProperties tokenProperties;

    public String generateToken(UserDetails user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 24)))
                .signWith(SignatureAlgorithm.HS256,tokenProperties.getSignKey())
                .compact();
    }

    public String getUsername(String token){
        return Jwts.parser().setSigningKey(tokenProperties.getSignKey().trim()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(tokenProperties.getSignKey()).parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
