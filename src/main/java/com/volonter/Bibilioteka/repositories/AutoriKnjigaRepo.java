package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.AutoriKnjiga;
import com.volonter.Bibilioteka.entities.Knjiga;
import org.springframework.data.repository.CrudRepository;

public interface AutoriKnjigaRepo extends CrudRepository<AutoriKnjiga,Integer> {
    Iterable<AutoriKnjiga> findAutoriKnjigaByKnjigaAutora(Knjiga knjiga);
}
