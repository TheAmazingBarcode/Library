package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Autor;
import com.volonter.Bibilioteka.entities.Knjiga;
import com.volonter.Bibilioteka.repositories.AutorRepo;
import com.volonter.Bibilioteka.repositories.AutoriKnjigaRepo;
import com.volonter.Bibilioteka.repositories.KnjigaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class KnjigeAutoraService {

    @Autowired
    private AutorRepo autorRepo;

    @Autowired
    private KnjigaRepo knjigaRepo;

    public List<Knjiga> knjigeAutora(Autor autor){
        return StreamSupport.stream(knjigaRepo.findKnjigasByAutori(autor).spliterator(),true).collect(Collectors.toList());
    }

    public List<Autor> autoriKnjige(Knjiga knjiga){
        return StreamSupport.stream(autorRepo.findAutorsByKnjige(knjiga).spliterator(),true).collect(Collectors.toList());
    }

}
