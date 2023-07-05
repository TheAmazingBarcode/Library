package com.volonter.Bibilioteka.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volonter.Bibilioteka.entities.Izdavac;
import com.volonter.Bibilioteka.entities.Knjiga;
import com.volonter.Bibilioteka.entities.Polica;
import com.volonter.Bibilioteka.services.KnjigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "knjiga")
public class KnjigaController {
    @Autowired
    private KnjigaService knjigaService;

    @GetMapping(path = "sve")
    public List<Knjiga> sve(){return knjigaService.sveKnjige();}

    @GetMapping(path = "pretraga/{naziv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigePoNazivu(@PathVariable String naziv){return knjigaService.knjigePoNaslovu(naziv);}

    @GetMapping(path = "autor/{ime}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigaPoAutoru(@PathVariable String ime){return knjigaService.knjigePoAutoru(ime);}

    @GetMapping(path = "polica",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigeNaPolici(@RequestBody Polica polica){return knjigaService.knjigeNaPolici(polica);}

    @GetMapping(path = "izdavac",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigePoIzdavacu(@RequestBody Izdavac izdavac){return knjigaService.knjigePoIzdavacu(izdavac);}

    @PostMapping(path = "nova",consumes =MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean novaKnjiga(@RequestParam(value = "json") String knjigaData, @RequestParam(value = "files") MultipartFile[] files){
        try {
            return knjigaService.kreirajKnjigu(new ObjectMapper().readValue(knjigaData,Knjiga.class),files);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(path = "izmeni",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Knjiga izmeniKnjigu(@RequestBody Knjiga knjiga){return knjigaService.izmeniKnjigu(knjiga);}

    @DeleteMapping(path = "izbrisi/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean izbrisiKnjigu(@PathVariable Integer id){return knjigaService.izbrisiKnjigu(id);}
}
