package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.*;
import com.volonter.Bibilioteka.repositories.AutorRepo;
import com.volonter.Bibilioteka.repositories.AutoriKnjigaRepo;
import com.volonter.Bibilioteka.repositories.KnjigaRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class KnjigaService {
    @Autowired
    private KnjigaRepo knjigaRepo;

    @Autowired
    private AutorRepo autorRepo;

    @Autowired
    private KnjigeAutoraService knjigeAutoraService;

    public boolean kreirajKnjigu(Knjiga knjiga){
        if(knjiga.getId() == null) {
            knjiga.getAutori().stream().forEach(autor -> autor.setAutoriKnjige
                    (autorRepo.findById(autor.getAutoriKnjige().getId()).get()));
            knjiga.getAutori().stream().forEach(autor -> autor.setKnjigaAutora(knjiga));
            knjigaRepo.save(knjiga);
            knjigeAutoraService.zapisiAutoreKnjiga(knjiga.getAutori());
            return true;
        }
        return false;
    }

    public List<Knjiga> sveKnjige(){
        return StreamSupport.stream(knjigaRepo.findAll().spliterator(),true).collect(Collectors.toList());
    }

    public List<Knjiga> knjigePoNaslovu(String naslov){
        return StreamSupport.stream(knjigaRepo.findKnjigasByNaslovContains(naslov).spliterator(),true).collect(Collectors.toList());
    }

    public List<Knjiga> knjigePoAutoru(String ime){
        return StreamSupport.stream(knjigaRepo.findKnjigasByAutori(autorRepo.findAutorByImeContains(ime)).spliterator(),true).collect(Collectors.toList());
    }

    public Knjiga izmeniKnjigu(Knjiga knjiga){
        knjigeAutoraService.izbrisiVezu(knjiga);
            knjiga.getAutori().stream().forEach(autor -> autor.setAutoriKnjige
                (autorRepo.findById(autor.getAutoriKnjige().getId()).get()));
            knjiga.getAutori().stream().forEach(autor -> autor.setKnjigaAutora(knjiga));
            knjigaRepo.save(knjiga);
            knjigeAutoraService.zapisiAutoreKnjiga(knjiga.getAutori());
        return knjigaRepo.findById(knjiga.getId()).get();
    }

    public List<Knjiga> knjigeNaPolici(Polica polica){
        return StreamSupport.stream(knjigaRepo.findKnjigasByPolica(polica).spliterator(),true).collect(Collectors.toList());
    }

    public List<Knjiga> knjigePoIzdavacu(Izdavac izdavac){
        return StreamSupport.stream(knjigaRepo.findKnjigasByIzdavac(izdavac).spliterator(),true).collect(Collectors.toList());
    }

    @Transactional
    public boolean izbrisiKnjigu(Knjiga knjiga) {
        try {
            knjigeAutoraService.izbrisiVezu(knjiga);
            knjigaRepo.delete(knjiga);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
