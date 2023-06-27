package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.entities.Korisnik;
import com.volonter.Bibilioteka.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "korisnik")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;

    @PostMapping(path = "registruj",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean registruj(@RequestBody Korisnik korisnik){
        return korisnikService.kreirajKorisnika(korisnik);
    }

    @PutMapping(path = "uloguj",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean uloguj(@RequestBody Korisnik korisnik){
        return korisnikService.ulogujKorisnika(korisnik);
    }
}
