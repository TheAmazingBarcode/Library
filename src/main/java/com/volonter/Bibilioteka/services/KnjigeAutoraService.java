package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.Autor;
import com.volonter.Bibilioteka.entities.AutoriKnjiga;
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
    private AutoriKnjigaRepo autoriKnjigaRepo;

    @Autowired
    private AutorRepo autorRepo;

    @Autowired
    private KnjigaRepo knjigaRepo;

    public List<Knjiga> knjigeAutora(Autor autor){
       return autoriKnjigaRepo.findAutoriKnjigaByAutorKnjige(autor).stream().map(AutoriKnjiga::getKnjigaAutora).toList();
    }

    public List<Autor> autoriKnjige(Knjiga knjiga){
        return autorRepo.findAutorsByKnjige(knjiga);
    }

    public void zapisiAutoreKnjiga(Iterable<AutoriKnjiga> autoriKnjiga){autoriKnjigaRepo.saveAll(autoriKnjiga);}

    public void izbrisiVezu(Knjiga knjiga){
        autoriKnjigaRepo.deleteAutoriKnjigaByKnjigaAutora(knjiga);
    }

}
