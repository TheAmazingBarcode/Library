package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.entities.Izdavac;
import com.volonter.Bibilioteka.entities.Knjiga;
import com.volonter.Bibilioteka.entities.Polica;
import com.volonter.Bibilioteka.services.KnjigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "knjiga")
public class KnjigaController {
    @Autowired
    private KnjigaService knjigaService;

    @GetMapping(path = "sve",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> sve(){return knjigaService.sveKnjige();}

    @GetMapping(path = "pretraga/{naziv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigePoNazivu(@PathVariable String naziv){return knjigaService.knjigePoNaslovu(naziv);}

    @GetMapping(path = "autor/{ime}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigaPoAutoru(@PathVariable String ime){return knjigaService.knjigePoAutoru(ime);}

    @GetMapping(path = "polica",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigeNaPolici(@RequestBody Polica polica){return knjigaService.knjigeNaPolici(polica);}

    @GetMapping(path = "izdavac",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigePoIzdavacu(@RequestBody Izdavac izdavac){return knjigaService.knjigePoIzdavacu(izdavac);}

    @PostMapping(path = "nova",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean novaKnjiga(@RequestBody Knjiga knjiga){return knjigaService.kreirajKnjigu(knjiga);}

    @PutMapping(path = "izmeni",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Knjiga izmeniKnjigu(@RequestBody Knjiga knjiga){return knjigaService.izmeniKnjigu(knjiga);}

    @DeleteMapping(path = "izbrisi",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean izbrisiKnjigu(@RequestBody Knjiga knjiga){return knjigaService.izbrisiKnjigu(knjiga);}
}
