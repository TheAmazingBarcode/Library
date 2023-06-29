package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.entities.Kategorija;
import com.volonter.Bibilioteka.services.KategorijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "kategorija")
public class KategorijaController {
    @Autowired
    private KategorijaService kategorijaService;

    @GetMapping(path = "sve",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Kategorija> sveKategorije(){
        return kategorijaService.prikazKategorija();
    }

    @GetMapping(path = "pretraga/{naziv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Kategorija> poNazivu(@PathVariable String naziv){return kategorijaService.prikazKategorijePoNazivu(naziv);}

    @PostMapping(path = "nova",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean kreirajNovu(@RequestBody Kategorija kategorija){return kategorijaService.kreirajKategoriju(kategorija);}

    @PutMapping(path = "izmeni",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Kategorija izmeniKategoriju(@RequestBody Kategorija kategorija){return kategorijaService.izmeniKategoriju(kategorija);}

    @DeleteMapping(path = "izbrisi",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean izbrisiKategoriju(@RequestBody Kategorija kategorija){return kategorijaService.izbrisiKategoriju(kategorija);}
}
