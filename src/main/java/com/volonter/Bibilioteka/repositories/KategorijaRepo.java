package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Kategorija;
import org.springframework.data.repository.CrudRepository;

public interface KategorijaRepo extends CrudRepository<Kategorija,Integer> {
    Iterable<Kategorija> findKategorijasByNazivContains(String ime);
}
