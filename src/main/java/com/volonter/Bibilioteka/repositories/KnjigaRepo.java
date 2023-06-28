package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.*;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.CrudRepository;

public interface KnjigaRepo extends CrudRepository<Knjiga,Integer> {
    Iterable<Knjiga> findKnjigasByAutori(Autor autor);

    Iterable<Knjiga> findKnjigasByNaslovContains(String naslov);

    Iterable<Knjiga> findKnjigasByNaslovIgnoreCase(String naslov);

    Iterable<Knjiga> findKnjigasByIzdavac(Izdavac izdavac);

    Iterable<Knjiga> findKnjigasByKategorija(Kategorija kategorija);

    Iterable<Knjiga> findKnjigasByPolica(Polica polica);

}
