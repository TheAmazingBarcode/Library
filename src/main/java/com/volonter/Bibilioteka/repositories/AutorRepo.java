package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.Autor;
import com.volonter.Bibilioteka.entities.Knjiga;
import org.springframework.data.repository.CrudRepository;

public interface AutorRepo extends CrudRepository<Autor,Integer> {
    Iterable<Autor> findAutorsByImeContains(String ime);

    Autor findAutorByImeContains(String ime);

    Iterable<Autor> findAutorsByKnjige(Knjiga knjiga);
}
