package com.volonter.Bibilioteka.repositories;

import com.volonter.Bibilioteka.entities.AutoriKnjiga;
import com.volonter.Bibilioteka.entities.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutoriKnjigaRepo extends JpaRepository<AutoriKnjiga,Integer> {
    List<AutoriKnjiga> findAutoriKnjigaByKnjigaAutora(Knjiga knjiga);

    void deleteAutoriKnjigaByKnjigaAutora(Knjiga knjiga);
}
