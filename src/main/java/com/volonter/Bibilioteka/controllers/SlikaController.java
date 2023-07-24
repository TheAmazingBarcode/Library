package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.entities.Knjiga;
import com.volonter.Bibilioteka.services.ImageService;
import com.volonter.Bibilioteka.services.KnjigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "slike")
@CrossOrigin
public class SlikaController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private KnjigaService knjigaService;

    @GetMapping(path = "knjiga/{strana}/{id}")
    public ResponseEntity<Resource> nadjiSliku(@PathVariable(name = "id")Integer id,@PathVariable(name = "strana") String strana){
        Knjiga knjiga = knjigaService.knjigaPoID(id);

        String putanja = "";

        if(strana.equalsIgnoreCase("prednja")){
            putanja = knjiga.getFotografijaPrednja();
        }
        else {
            putanja = knjiga.getFotografijaZadnja();
        }

        Resource slika = imageService.nadjiSliku(putanja);

        System.out.println(slika.getFilename());

        if(slika.exists()){
            return ResponseEntity.ok()
                    .contentType(imageService.tipSlike(putanja))
                    .body(slika);
        }
        else
           return ResponseEntity.notFound().build();
    }
}
