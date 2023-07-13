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
        return prostorijaRepo.findAll();
    }

    public List<Prostorija> prostorijePoNazivu(String naziv){
        return prostorijaRepo.findProstorijasByNazivContains(naziv);
    }

    public List<Polica> policeProstorije(Integer id){
        return policaService.policePoProstoriji(id);
    }

    public Prostorija izmeniProstoriju(Prostorija prostorija){return prostorijaRepo.save(prostorija);}

    public boolean izbrisiProstoriju(Integer id){
        if(policaService.policePoProstoriji(id).isEmpty()){
            prostorijaRepo.deleteById(id);
            return true;
        }
        else
            return false;
    }

}
