package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Autor;
import com.volonter.Bibilioteka.entities.Izdavac;
import com.volonter.Bibilioteka.repositories.IzdavacRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IzdavacService {
    @Autowired
    private IzdavacRepo izdavacRepo;

    public boolean kreirajIzadavaca(Izdavac izdavac){
        if(izdavac.getId() == null){
            izdavacRepo.save(izdavac);
            return true;
        }
        else
            return false;
    }

    public List<Izdavac> sviIzdavaci(){
        return StreamSupport.stream(izdavacRepo.findAll().spliterator(),true).collect(Collectors.toList());
    }

    public List<Izdavac> izdavaciPoNazivu(String naziv){
        return StreamSupport.stream(izdavacRepo.findIzdavacsByNazivContains(naziv).spliterator(),true).collect(Collectors.toList());
    }

    public Izdavac izmeniIzdavaca(Izdavac izdavac) {
        return izdavacRepo.save(izdavac);
    }


    //Opasno brisati izdavaca, moguce samo ukoliko nema knjige koje pripadaju datom izdavacu
/*    public void izbrisiIzdavaca(Izdavac izdavac){
        izdavacRepo.delete(izdavac);
    }*/
}
