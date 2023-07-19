package com.volonter.Bibilioteka.security;

import com.volonter.Bibilioteka.entities.Korisnik;
import com.volonter.Bibilioteka.repositories.KorisnikRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private KorisnikRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik = repo.findKorisnikByUsername(username);
        return User.builder().username(korisnik.getUsername()).password(korisnik.getPassword()).build();
    }
}
