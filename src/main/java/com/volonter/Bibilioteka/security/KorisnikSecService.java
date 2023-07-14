package com.volonter.Bibilioteka.security;

import com.volonter.Bibilioteka.entities.Korisnik;
import com.volonter.Bibilioteka.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class KorisnikSecService implements UserDetailsService {
    @Autowired
    private KorisnikService korisnikService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikService.nadjiPoUsername(username);
        if(korisnik == null){
            new UsernameNotFoundException("Ne postoji korisnik");
        }

        return new User(korisnik.getUsername(), korisnik.getPassword(), Collections.emptyList());
    }
}
