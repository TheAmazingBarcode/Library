package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Autor;
import com.volonter.Bibilioteka.repositories.AutorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AutorService {
    @Autowired
    private AutorRepo autorRepo;

    public boolean kreirajAutora(Autor autor){
        if(autor.getId() == null){
            autorRepo.save(autor);
            return true;
        }
        else
            return false;
    }

    public List<Autor> prikazAutora(){
        return StreamSupport.stream(autorRepo.findAll().spliterator(),true).collect(Collectors.toList());
    }

    public List<Autor> prikazAutoraPoImenu(String ime){
        return StreamSupport.stream(autorRepo.findAutorsByImeContains(ime).spliterator(),true).collect(Collectors.toList());
    }
}
