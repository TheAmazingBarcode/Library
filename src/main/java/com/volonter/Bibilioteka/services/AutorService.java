package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Autor;
import com.volonter.Bibilioteka.repositories.AutorRepo;
import com.volonter.Bibilioteka.repositories.AutoriKnjigaRepo;
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
    private AutoriKnjigaRepo autoriKnjigaRepo;

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
        return autorRepo.findAll();
    }

    public List<Autor> prikazAutoraPoImenu(String ime){
        return autorRepo.findAutorsByImeContains(ime);
    }

    public Autor izmeniAutora(Autor autor){
        return autorRepo.save(autor);
    }

    public boolean izbrisiAutora(Integer id){
            if (autoriKnjigaRepo.findAutoriKnjigaByAutorKnjige(autorRepo.findById(id).get()).isEmpty()) {
                autorRepo.deleteById(id);
                return true;
            }
        return false;
    }
}
