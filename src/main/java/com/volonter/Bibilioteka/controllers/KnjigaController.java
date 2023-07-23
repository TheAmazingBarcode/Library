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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(path = "knjiga")
public class KnjigaController {
    @Autowired
    private KnjigaService knjigaService;

    @GetMapping(path = "sve")
    public List<Knjiga> sve(){return knjigaService.sveKnjige();}

    @GetMapping(path = "pretraga/{naziv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigePoNazivu(@PathVariable String naziv){return knjigaService.knjigePoNaslovu(naziv);}

    @GetMapping(path = "autor/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigaPoAutoru(@PathVariable("id") Integer id){return knjigaService.knjigePoAutoru(id);}

    @GetMapping(path = "kategorija/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> knjigePoKategoriji(@PathVariable("id")Integer id){return knjigaService.knjigePoKategoriji(id);}

    @GetMapping(path = "polica/{id}")
    public List<Knjiga> knjigeNaPolici(@PathVariable Integer id){return knjigaService.knjigeNaPolici(id);}

    @GetMapping(path = "izdavac/{id}")
    public List<Knjiga> knjigePoIzdavacu(@PathVariable("id") Integer izdavac){return knjigaService.knjigePoIzdavacu(izdavac);}

    @PostMapping(path = "nova",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean novaKnjiga(@RequestParam(value = "data") MultipartFile knjigaData, @RequestParam(value = "files") MultipartFile[] files){
        try {
            return knjigaService.kreirajKnjigu(new ObjectMapper().readValue(knjigaData.getInputStream().readAllBytes(),Knjiga.class),files);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(path = "izmeni",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Knjiga izmeniKnjigu(@RequestBody Knjiga knjiga){return knjigaService.izmeniKnjigu(knjiga);}

    @DeleteMapping(path = "izbrisi/{id}")
    public boolean izbrisiKnjigu(@PathVariable Integer id) {return knjigaService.izbrisiKnjigu(id);}
}
