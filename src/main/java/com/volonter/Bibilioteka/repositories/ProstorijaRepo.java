package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Prostorija;
import org.springframework.data.repository.CrudRepository;

public interface ProstorijaRepo extends CrudRepository<Prostorija,Integer> {
    Iterable<Prostorija> findProstorijasByNazivContains(String naziv);
}
