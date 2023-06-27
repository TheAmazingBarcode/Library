package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.entities.Polica;
import com.volonter.Bibilioteka.entities.Prostorija;
import com.volonter.Bibilioteka.services.ProstorijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "prostorija")
public class ProstorijaController {
    @Autowired
    private ProstorijaService prostorijaService;

    @GetMapping(path = "sve",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Prostorija> sveKategorije(){
        return prostorijaService.sveProstorije();
    }

    @GetMapping(path = "pretraga/{naziv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Prostorija> poNazivu(@PathVariable String naziv){
        return prostorijaService.prostorijePoNazivu(naziv);
    }

    @PutMapping(path = "police",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Polica> police(@RequestBody Prostorija prostorija){ return prostorijaService.policeProstorije(prostorija); }

    @PostMapping(path = "nova",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean kreirajNovu(@RequestBody Prostorija prostorija){ return prostorijaService.kreirajProstoriju(prostorija); }
}
