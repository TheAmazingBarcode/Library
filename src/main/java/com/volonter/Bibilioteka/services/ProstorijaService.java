package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Polica;
import com.volonter.Bibilioteka.entities.Prostorija;
import com.volonter.Bibilioteka.repositories.ProstorijaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProstorijaService {
    @Autowired
    private ProstorijaRepo prostorijaRepo;

    @Autowired
    private PolicaService policaService;

    public boolean kreirajProstoriju(Prostorija prostorija){
        if(prostorija.getId() == null){
            prostorijaRepo.save(prostorija);
            return true;
        }
        else
            return false;
    }

    public List<Prostorija> sveProstorije(){
        return StreamSupport.stream(prostorijaRepo.findAll().spliterator(),true).collect(Collectors.toList());
    }

    public List<Prostorija> prostorijePoNazivu(String naziv){
        return StreamSupport.stream(prostorijaRepo.findProstorijasByNazivContains(naziv).spliterator(),true).collect(Collectors.toList());
    }

    public List<Polica> policeProstorije(Prostorija prostorija){
        return policaService.policePoProstoriji(prostorija);
    }
}
