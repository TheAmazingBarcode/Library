package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Izdavac;
import com.volonter.Bibilioteka.entities.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IzdavacRepo extends JpaRepository<Izdavac,Integer> {
    List<Izdavac> findIzdavacsByNazivContains(String naziv);
}
