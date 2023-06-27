package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Kategorija;
import com.volonter.Bibilioteka.repositories.KategorijaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class KategorijaService {
    @Autowired
    private KategorijaRepo kategorijaRepo;

    public boolean kreirajKategoriju(Kategorija kategorija){
        if(kategorija.getId() == null){
            kategorijaRepo.save(kategorija);
            return true;
        }
        else
            return false;
    }

    public List<Kategorija> prikazKategorija(){
        return StreamSupport.stream(kategorijaRepo.findAll().spliterator(),true).collect(Collectors.toList());
    }

    public List<Kategorija> prikazKategorijePoNazivu(String naziv){
        return StreamSupport.stream(kategorijaRepo.findKategorijasByNazivContains(naziv).spliterator(),true).collect(Collectors.toList());
    }

    public Kategorija izmeniKategoriju(Kategorija kategorija){
        return kategorijaRepo.save(kategorija);
    }

    public void izbrisiKategoriju(Kategorija kategorija){
        kategorijaRepo.delete(kategorija);
    }
}
