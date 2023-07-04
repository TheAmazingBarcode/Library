package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Autor;
import com.volonter.Bibilioteka.entities.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutorRepo extends JpaRepository<Autor,Integer> {
    List<Autor> findAutorsByImeContains(String ime);

    Autor findAutorByImeContains(String ime);

    List<Autor> findAutorsByKnjige(Knjiga knjiga);
}
