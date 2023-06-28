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
      if(korisnik.getId() == null) {
          korisnici.save(korisnik);
          return true;
      }
        return false;
    }

    public boolean ulogujKorisnika(Korisnik korisnik){
        return korisnici.existsKorisnikByUsernameAndPassword(korisnik.getUsername(), korisnik.getPassword());
    }

    public Korisnik izmeniKorisnika(Korisnik korisnik){
        return korisnici.save(korisnik);
    }

    public boolean izbrisiKorisnika(Korisnik korisnik){
      try{
          korisnici.delete(korisnik);
          return true;
      }
      catch (Exception e){
          return false;
      }
    }

}
