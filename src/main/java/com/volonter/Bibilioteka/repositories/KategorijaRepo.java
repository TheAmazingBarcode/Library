package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Kategorija;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KategorijaRepo extends JpaRepository<Kategorija,Integer> {
    List<Kategorija> findKategorijasByNazivContains(String ime);
}
