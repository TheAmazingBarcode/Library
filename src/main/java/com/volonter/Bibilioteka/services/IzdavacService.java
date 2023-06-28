package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Autor;
import com.volonter.Bibilioteka.entities.Izdavac;
import com.volonter.Bibilioteka.repositories.IzdavacRepo;
import com.volonter.Bibilioteka.repositories.KnjigaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IzdavacService {
    @Autowired
    private IzdavacRepo izdavacRepo;

    @Autowired
    private KnjigaRepo knjigaRepo;


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


    public boolean izbrisiIzadavaca(Izdavac izdavac){
        if(StreamSupport.stream(knjigaRepo.findKnjigasByIzdavac(izdavac).spliterator(),true).collect(Collectors.toList()).isEmpty()){
            izdavacRepo.delete(izdavac);
            return true;
        }
        else
            return false;
    }
}
