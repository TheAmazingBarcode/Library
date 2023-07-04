package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnjigaRepo extends JpaRepository<Knjiga, Integer> {
    List<Knjiga> findKnjigasByAutori(Autor autor);

    List<Knjiga> findKnjigasByNaslovContains(String naslov);

    List<Knjiga> findKnjigasByNaslovIgnoreCase(String naslov);

    List<Knjiga> findKnjigasByIzdavac(Izdavac izdavac);

    List<Knjiga> findKnjigasByKategorija(Kategorija kategorija);

    List<Knjiga> findKnjigasByPolica(Polica polica);

}
