package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.entities.Izdavac;
import com.volonter.Bibilioteka.entities.Kategorija;
import com.volonter.Bibilioteka.services.IzdavacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "izdavac")
public class IzdavacController {
    @Autowired
    private IzdavacService izdavacService;

    @GetMapping(path = "svi",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Izdavac> sviIzdavaci(){
        return izdavacService.sviIzdavaci();
    }

    @GetMapping(path = "pretraga/{naziv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Izdavac> poNazivu(@PathVariable String naziv){
        return izdavacService.izdavaciPoNazivu(naziv);
    }

    @PostMapping(path = "novi",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean kreirajNovi(@RequestBody Izdavac izdavac){
        return izdavacService.kreirajIzadavaca(izdavac);
    }

    @PutMapping(path = "izmeni",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Izdavac izmeniIzdavaca(@RequestBody Izdavac izdavac){return izdavacService.izmeniIzdavaca(izdavac);}

    @DeleteMapping(path = "izbrisi/{id}")
    public boolean izbrisi(@PathVariable(name = "id") Izdavac izdavac){return izdavacService.izbrisiIzadavaca(izdavac);}
}
