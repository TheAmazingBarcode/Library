package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Izdavac;
import com.volonter.Bibilioteka.entities.Knjiga;
import org.springframework.data.repository.CrudRepository;

public interface IzdavacRepo extends CrudRepository<Izdavac,Integer> {
    Iterable<Izdavac> findIzdavacsByNazivContains(String naziv);
}
