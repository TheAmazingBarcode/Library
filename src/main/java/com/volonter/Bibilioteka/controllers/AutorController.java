package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.entities.Autor;
import com.volonter.Bibilioteka.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "autor")
public class AutorController {
    @Autowired
    private AutorService autorService;

    @GetMapping(path = "svi",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Autor> sviAutori(){
        return autorService.prikazAutora();
    }

    @GetMapping(path = "pretraga/{ime}")
    public List<Autor> poImenu(@PathVariable String ime){
        return autorService.prikazAutoraPoImenu(ime);
    }

    @PostMapping(path = "novi",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean kreirajNovog(@RequestBody Autor autor){
        return autorService.kreirajAutora(autor);
    }

    @PutMapping(path = "izmeni",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Autor izmeniAutora(@RequestBody Autor autor){ return autorService.izmeniAutora(autor); }

    @DeleteMapping
    public void izbrisiAutora(@RequestBody Autor autor){ autorService.izbrisiAutora(autor); }
}
