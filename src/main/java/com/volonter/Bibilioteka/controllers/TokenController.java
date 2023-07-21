package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.security.UserServiceImpl;
import com.volonter.Bibilioteka.security.jwt.TokenDTO;
import com.volonter.Bibilioteka.security.jwt.TokenService;
import com.volonter.Bibilioteka.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserServiceImpl userService;

    @PutMapping(path = "verify")
    public TokenDTO token(@RequestBody TokenDTO token){
        if(tokenService.validateToken(token.getValue())){
            return new TokenDTO(token.getValue());
        }
        return new TokenDTO(tokenService.generateToken(userService.loadUserByUsername(tokenService.getUsername(token.getValue()))));
    }
}
