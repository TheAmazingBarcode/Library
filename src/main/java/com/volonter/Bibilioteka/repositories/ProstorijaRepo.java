package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Prostorija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProstorijaRepo extends JpaRepository<Prostorija,Integer> {
    List<Prostorija> findProstorijasByNazivContains(String naziv);
}
