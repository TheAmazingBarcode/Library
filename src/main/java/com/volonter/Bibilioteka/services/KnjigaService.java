package com.volonter.Bibilioteka.services;

import com.volonter.Bibilioteka.entities.*;
import com.volonter.Bibilioteka.repositories.AutorRepo;
import com.volonter.Bibilioteka.repositories.AutoriKnjigaRepo;
import com.volonter.Bibilioteka.repositories.KnjigaRepo;
import com.volonter.Bibilioteka.repositories.PolicaRepo;
import com.volonter.Bibilioteka.utility.ImgUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class KnjigaService {
    @Autowired
    private KnjigaRepo knjigaRepo;

    @Autowired
    private AutorRepo autorRepo;

    @Autowired
    private PolicaService policaService;

    @Autowired
    private IzdavacService izdavacService;

    @Autowired
    private KnjigeAutoraService knjigeAutoraService;

    public boolean kreirajKnjigu(Knjiga knjiga, MultipartFile[] files){
        if(knjiga.getId() == null) {
            knjiga.getAutori().stream().forEach(autor -> autor.setAutoriKnjige
                    (autorRepo.findById(autor.getAutoriKnjige().getId()).get()));
            knjiga.getAutori().stream().forEach(autor -> autor.setKnjigaAutora(knjiga));

            if(files!= null) {
                String folder = "/home/mateja/Desktop/Development/BackEndDATA/BibliotekaIMG/";
                String fileNames[] = Arrays.stream(files).map(MultipartFile::getOriginalFilename).toArray(String[]::new);

                Arrays.stream(files).forEach(file -> {
                    try {
                        ImgUtil.sacuvajFile(file, folder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                knjiga.setFotografijaPrednja(folder + fileNames[0]);
                knjiga.setFotografijaZadnja(folder + fileNames[1]);
            }
            knjigaRepo.save(knjiga);
            knjigeAutoraService.zapisiAutoreKnjiga(knjiga.getAutori());
            return true;
        }
        return false;
    }

    public List<Knjiga> sveKnjige(){
       List<Knjiga> knjige =  knjigaRepo.findAll();
       knjige.forEach(knjiga -> System.out.println(knjiga.getKategorija().getNaziv()));
       return knjige;
    }

    public List<Knjiga> knjigePoNaslovu(String naslov){
        return knjigaRepo.findKnjigasByNaslovContains(naslov);
    }

    public List<Knjiga> knjigePoAutoru(String ime){
        return knjigaRepo.findKnjigasByAutori(autorRepo.findAutorByImeContains(ime));
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
        return knjigaRepo.findKnjigasByPolica(policaService.policePoNazivu(polica.getNaziv()).get(0));
    }

    public List<Knjiga> knjigePoIzdavacu(Izdavac izdavac){
        return knjigaRepo.findKnjigasByIzdavac(izdavacService.izdavaciPoNazivu(izdavac.getNaziv()).get(0));
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
