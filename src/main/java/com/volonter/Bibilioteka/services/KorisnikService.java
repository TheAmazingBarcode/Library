package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Korisnik;
import com.volonter.Bibilioteka.repositories.KorisnikRepo;
import com.volonter.Bibilioteka.security.UserServiceImpl;
import com.volonter.Bibilioteka.security.jwt.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepo korisnici;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserServiceImpl securityUser;

    public String kreirajKorisnika(Korisnik korisnik) throws Exception {
      if(korisnik.getId() == null && !korisnici.existsKorisnikByUsernameAndPassword(korisnik.getUsername(),korisnik.getPassword())) {
          korisnici.save(korisnik);
          return tokenService.generateToken(securityUser.loadUserByUsername(korisnik.getUsername()));
      }
        throw new Exception("Nepravilan format");
    }

    public String ulogujKorisnika(Korisnik korisnik){
        if(korisnici.existsKorisnikByUsernameAndPassword(korisnik.getUsername(), korisnik.getPassword())){
            return tokenService.generateToken(securityUser.loadUserByUsername(korisnik.getUsername()));
        }
        throw new UsernameNotFoundException("Ne postoji korisnik");
    }
    public Korisnik nadjiPoUsername(String username){
        return korisnici.findKorisnikByUsername(username);
    }

    public Korisnik izmeniKorisnika(Korisnik korisnik){
        return korisnici.save(korisnik);
    }

    public boolean izbrisiKorisnika(Integer id){
      try{
          korisnici.deleteById(id);
          return true;
      }
      catch (Exception e){
          return false;
      }
    }

    public Integer nadjiId(String username){
        return korisnici.findKorisnikByUsername(username).getId();
    }
}
