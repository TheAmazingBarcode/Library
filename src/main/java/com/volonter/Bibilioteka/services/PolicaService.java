package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Polica;
import com.volonter.Bibilioteka.entities.Prostorija;
import com.volonter.Bibilioteka.repositories.KnjigaRepo;
import com.volonter.Bibilioteka.repositories.PolicaRepo;
import com.volonter.Bibilioteka.repositories.ProstorijaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PolicaService {
    @Autowired
    private PolicaRepo policaRepo;

    @Autowired
    private KnjigaRepo knjigaRepo;

    public boolean kreirajPolicu(Polica polica){
        if(polica.getId() == null){
            policaRepo.save(polica);
            return true;
        }
        else
            return false;
    }

    public List<Polica> svePolice(){
        return StreamSupport.stream(policaRepo.findAll().spliterator(),true).collect(Collectors.toList());
    }

    public List<Polica> policePoNazivu(String naziv){
        return StreamSupport.stream(policaRepo.findPolicasByNazivContains(naziv).spliterator(),true).collect(Collectors.toList());
    }

    public List<Polica> policePoProstoriji(Prostorija prostorija){
        return StreamSupport.stream(policaRepo.findPolicasByProstorija(prostorija).spliterator(),true).collect(Collectors.toList());
    }

    public Polica izmeniPolicu(Polica polica){
        return policaRepo.save(polica);
    }

    public boolean izbrisiPolicu(Polica polica){
        if(StreamSupport.stream(knjigaRepo.findKnjigasByPolica(polica).spliterator(),true).collect(Collectors.toList()).isEmpty()){
            policaRepo.delete(polica);
            return true;
        }
        else
            return false;
    }
}
