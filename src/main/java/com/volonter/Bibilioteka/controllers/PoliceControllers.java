package com.volonter.Bibilioteka.controllers;


import com.volonter.Bibilioteka.entities.Polica;
import com.volonter.Bibilioteka.entities.Prostorija;
import com.volonter.Bibilioteka.services.PolicaService;
import com.volonter.Bibilioteka.services.ProstorijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "police")
public class PoliceControllers {
    @Autowired
    private PolicaService policaService;

    @GetMapping(path = "sve",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Polica> sveKategorije(){
        return policaService.svePolice();
    }

    @GetMapping(path = "pretraga/{naziv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Polica> poNazivu(@PathVariable String naziv){return policaService.policePoNazivu(naziv);}

    @PostMapping(path = "nova",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean kreirajNovu(@RequestBody Polica prostorija){return policaService.kreirajPolicu(prostorija);}

    @PutMapping(path = "izmeni",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Polica izmeniPolicu(@RequestBody Polica polica){return policaService.izmeniPolicu(polica);}

    @DeleteMapping(path = "izbrisi",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean izbrisiPolicu(@RequestBody Polica polica){return policaService.izbrisiPolicu(polica);}
}
