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
        return izdavacRepo.findAll();
    }

    public Izdavac izdavacPoID(Integer id){
        return izdavacRepo.findById(id).get();
    }

    public List<Izdavac> izdavaciPoNazivu(String naziv){
        return izdavacRepo.findIzdavacsByNazivContains(naziv);
    }

    public Izdavac izmeniIzdavaca(Izdavac izdavac) {
        return izdavacRepo.save(izdavac);
    }


    public boolean izbrisiIzadavaca(Izdavac izdavac){
        if(knjigaRepo.findKnjigasByIzdavac(izdavac).isEmpty()){
            izdavacRepo.delete(izdavac);
            return true;
        }
        else
            return false;
    }
}
