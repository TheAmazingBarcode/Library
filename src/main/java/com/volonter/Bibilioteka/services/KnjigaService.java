package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Autor;
import com.volonter.Bibilioteka.entities.Knjiga;
import com.volonter.Bibilioteka.repositories.AutorRepo;
import com.volonter.Bibilioteka.repositories.KnjigaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class KnjigaService {
    @Autowired
    private KnjigaRepo knjigaRepo;

    @Autowired
    private AutorRepo autorRepo;

    public boolean kreirajKnjigu(Knjiga knjiga){
        if(knjiga.getId() == null) {
            knjigaRepo.save(knjiga);
            return true;
        }
        return false;
    }

    public List<Knjiga> sveKnjige(){
        return StreamSupport.stream(knjigaRepo.findAll().spliterator(),true).collect(Collectors.toList());
    }

    public List<Knjiga> knjigePoNaslovu(String naslov){
        return StreamSupport.stream(knjigaRepo.findKnjigasByNaslovContains(naslov).spliterator(),true).collect(Collectors.toList());
    }

    public List<Knjiga> knjigePoAutoru(String ime){
        return StreamSupport.stream(knjigaRepo.findKnjigasByAutori(autorRepo.findAutorByImeContains(ime)).spliterator(),true).collect(Collectors.toList());
    }
}
