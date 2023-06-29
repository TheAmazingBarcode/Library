package com.volonter.Bibilioteka.controllers;

import com.volonter.Bibilioteka.entities.Knjiga;
import com.volonter.Bibilioteka.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path = "pretraga")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping(path = "kriterijum",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Knjiga> filtriraneKnjige(@RequestBody Map<String, Object> kriterijumi){
        return searchService.knjigePoKriterijumu(kriterijumi);
    }
}
