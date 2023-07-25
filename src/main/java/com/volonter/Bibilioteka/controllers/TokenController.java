package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.security.UserServiceImpl;
import com.volonter.Bibilioteka.security.encryption.Encryptor;
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

    @Autowired
    private Encryptor encryptor;

    @PutMapping(path = "verify/{username}")
    public TokenDTO token(@RequestBody TokenDTO token,@PathVariable("username")String username){
        if(tokenService.validateToken(token.getValue())){
            return new TokenDTO(token.getValue());
        }
        return new TokenDTO(tokenService.generateToken(userService.loadUserByUsername(encryptor.encrypt(username))));
    }
}
