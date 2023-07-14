package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Korisnik;
import com.volonter.Bibilioteka.repositories.KorisnikRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepo korisnici;

    public boolean kreirajKorisnika(Korisnik korisnik){
      if(korisnik.getId() == null && !korisnici.existsKorisnikByUsernameAndPassword(korisnik.getUsername(),korisnik.getPassword())) {
          korisnici.save(korisnik);
          return true;
      }
        return false;
    }

    public boolean ulogujKorisnika(Korisnik korisnik){
        return korisnici.existsKorisnikByUsernameAndPassword(korisnik.getUsername(), korisnik.getPassword());
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
