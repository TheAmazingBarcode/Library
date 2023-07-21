package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Korisnik;
import com.volonter.Bibilioteka.repositories.KorisnikRepo;
import com.volonter.Bibilioteka.security.UserServiceImpl;
import com.volonter.Bibilioteka.security.encryption.Decryptor;
import com.volonter.Bibilioteka.security.encryption.Encryptor;
import com.volonter.Bibilioteka.security.jwt.TokenDTO;
import com.volonter.Bibilioteka.security.jwt.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Decryptor decryptor;

    @Autowired
    private Encryptor encryptor;

    public TokenDTO kreirajKorisnika(Korisnik korisnik) throws Exception {
      if(korisnik.getId() == null && !korisnici.existsKorisnikByUsernameAndPassword(decryptor.decrypt(korisnik.getUsername()),decryptor.decrypt(korisnik.getPassword()))) {
          korisnik.setUsername(encryptor.encrypt(korisnik.getUsername()));
          korisnik.setPassword(encryptor.encrypt(korisnik.getPassword()));
          korisnici.save(korisnik);
          return new TokenDTO(tokenService.generateToken(securityUser.loadUserByUsername(korisnik.getUsername())));
      }
        throw new Exception("Nepravilan format");
    }

    public TokenDTO ulogujKorisnika(Korisnik korisnik){
        if(korisnici.existsKorisnikByUsernameAndPassword(encryptor.encrypt(korisnik.getUsername()), encryptor.encrypt(korisnik.getPassword()))){
            return new TokenDTO(tokenService.generateToken(securityUser.loadUserByUsername(encryptor.encrypt(korisnik.getUsername()))));
        }
        throw new UsernameNotFoundException("Ne postoji korisnik");
    }
    public Korisnik nadjiPoUsername(String username){
        return korisnici.findKorisnikByUsername(encryptor.encrypt(username));
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
