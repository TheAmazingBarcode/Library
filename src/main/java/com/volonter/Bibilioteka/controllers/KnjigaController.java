package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.entities.Knjiga;
import com.volonter.Bibilioteka.services.KnjigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "knjiga")
public class KnjigaController {
    @Autowired
    private KnjigaService knjigaService;

    @GetMapping(path = "sve",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> sve(){return knjigaService.sveKnjige();}

    @GetMapping(path = "pretraga/{naziv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigePoNazivu(@PathVariable String naziv){return knjigaService.knjigePoNaslovu(naziv);}

    @PutMapping(path = "autor/{ime}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigaPoAutoru(@PathVariable String ime){return knjigaService.knjigePoAutoru(ime);}

    @PostMapping(path = "nova",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean novaKnjiga(@RequestBody Knjiga knjiga){return knjigaService.kreirajKnjigu(knjiga);}
}
