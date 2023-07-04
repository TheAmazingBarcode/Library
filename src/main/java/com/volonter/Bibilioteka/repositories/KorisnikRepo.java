package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface KorisnikRepo extends JpaRepository<Korisnik,Integer> {
    boolean existsKorisnikByUsernameAndPassword(String username,String password);
}
