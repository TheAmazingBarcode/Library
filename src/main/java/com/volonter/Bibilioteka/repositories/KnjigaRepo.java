package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Autor;
import com.volonter.Bibilioteka.entities.Knjiga;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.CrudRepository;

public interface KnjigaRepo extends CrudRepository<Knjiga,Integer> {
    Iterable<Knjiga> findKnjigasByAutori(Autor autor);

    Iterable<Knjiga> findKnjigasByNaslovContains(String naslov);

    Iterable<Knjiga> findKnjigaByNaslovIgnoreCase(String naslov);

}
