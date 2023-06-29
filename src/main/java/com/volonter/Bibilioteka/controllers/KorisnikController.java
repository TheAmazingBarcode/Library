package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.entities.Korisnik;
import com.volonter.Bibilioteka.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "korisnik")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;

    @PostMapping(path = "registruj",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean registruj(@RequestBody Korisnik korisnik){
        return korisnikService.kreirajKorisnika(korisnik);
    }

    @GetMapping(path = "uloguj",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean uloguj(@RequestBody Korisnik korisnik){
        return korisnikService.ulogujKorisnika(korisnik);
    }

    @PutMapping(path = "izmeni",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Korisnik korisnik(@RequestBody Korisnik korisnik){return korisnikService.izmeniKorisnika(korisnik);}

    @DeleteMapping(path = "izbrisi",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean izbrisiKorisnika(@RequestBody Korisnik korisnik){return korisnikService.izbrisiKorisnika(korisnik);}
}
