package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Polica;
import com.volonter.Bibilioteka.entities.Prostorija;
import org.springframework.data.jpa.repository.JpaRepository;;

import java.util.List;

public interface PolicaRepo extends JpaRepository<Polica,Integer> {
    List<Polica> findPolicasByNazivContains(String naziv);


    List<Polica> findPolicasByProstorija(Prostorija prostorija);
}
