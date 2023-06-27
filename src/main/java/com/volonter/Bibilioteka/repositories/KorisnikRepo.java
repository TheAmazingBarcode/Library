package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Korisnik;
import org.springframework.data.repository.CrudRepository;

public interface KorisnikRepo extends CrudRepository<Korisnik,Integer> {
    boolean existsKorisnikByUsernameAndPassword(String username,String password);
}
