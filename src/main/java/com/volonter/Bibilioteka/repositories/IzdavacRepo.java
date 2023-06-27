package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Izdavac;
import org.springframework.data.repository.CrudRepository;

public interface IzdavacRepo extends CrudRepository<Izdavac,Integer> {
    Iterable<Izdavac> findIzdavacsByNazivContains(String naziv);
}
