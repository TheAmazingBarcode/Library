package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Polica;
import com.volonter.Bibilioteka.entities.Prostorija;
import org.springframework.data.repository.CrudRepository;

public interface PolicaRepo extends CrudRepository<Polica,Integer> {
    Iterable<Polica> findPolicasByNazivContains(String naziv);

    Iterable<Polica> findPolicasByProstorija(Prostorija prostorija);
}
