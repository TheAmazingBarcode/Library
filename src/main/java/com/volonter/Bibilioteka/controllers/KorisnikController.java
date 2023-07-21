package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.entities.Korisnik;
import com.volonter.Bibilioteka.security.jwt.TokenDTO;
import com.volonter.Bibilioteka.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@RestController
@CrossOrigin
@RequestMapping(path = "korisnik")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;

    @PostMapping(path = "registruj",consumes = MediaType.APPLICATION_JSON_VALUE)
    public TokenDTO registruj(@RequestBody Korisnik korisnik) throws Exception {
        return korisnikService.kreirajKorisnika(korisnik);
    }

    @PutMapping(path = "uloguj",consumes = MediaType.APPLICATION_JSON_VALUE)
    public TokenDTO uloguj(@RequestBody Korisnik korisnik){return korisnikService.ulogujKorisnika(korisnik);}

    @GetMapping(path = "id/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer nadjiId(@PathVariable(name = "username") String username){return korisnikService.nadjiId(username);}

    @PutMapping(path = "izmeni",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Korisnik korisnik(@RequestBody Korisnik korisnik){return korisnikService.izmeniKorisnika(korisnik);}

    @DeleteMapping(path = "izbrisi/{id}")
    public boolean izbrisiKorisnika(@PathVariable(name = "id") Integer id){return korisnikService.izbrisiKorisnika(id);}
}
