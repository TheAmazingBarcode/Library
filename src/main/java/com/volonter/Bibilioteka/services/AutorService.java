package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Autor;
import com.volonter.Bibilioteka.repositories.AutorRepo;
import com.volonter.Bibilioteka.repositories.KnjigaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AutorService {
    @Autowired
    private AutorRepo autorRepo;

    @Autowired
    private KnjigaRepo knjigaRepo;

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

    public Autor izmeniAutora(Autor autor){
        return autorRepo.save(autor);
    }

    public boolean izbrisiAutora(Autor autor){
            if (StreamSupport.stream(knjigaRepo.findKnjigasByAutori(autor).spliterator(), true).collect(Collectors.toList()).isEmpty()) {
                autorRepo.delete(autor);
                return true;
            }
        return false;
    }
}
