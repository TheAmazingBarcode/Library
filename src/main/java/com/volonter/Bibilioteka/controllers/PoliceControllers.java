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
@CrossOrigin
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

    @GetMapping(path = "prostorija/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Polica> policePoProstoriji(@PathVariable(name = "id") Integer id){return policaService.policePoProstoriji(id);}

    @PostMapping(path = "nova",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean kreirajNovu(@RequestBody Polica polica){return policaService.kreirajPolicu(polica);}

    @PutMapping(path = "izmeni",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Polica izmeniPolicu(@RequestBody Polica polica){return policaService.izmeniPolicu(polica);}

    @DeleteMapping(path = "izbrisi/{id}")
    public boolean izbrisiPolicu(@PathVariable(name = "id")Integer id){return policaService.izbrisiPolicu(id);}
}
