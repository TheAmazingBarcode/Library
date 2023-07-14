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

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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

    @Autowired
    private KategorijaService kategorijaService;

    public boolean kreirajKnjigu(Knjiga knjiga, MultipartFile[] files){
        if(knjiga.getId() == null) {
            knjiga.getAutori().stream().forEach(autor -> autor.setAutoriKnjige
                    (autorRepo.findById(autor.getAutoriKnjige().getId()).get()));
            knjiga.getAutori().stream().forEach(autor -> autor.setKnjigaAutora(knjiga));

            if(files!= null) {
                String separator = File.separator;
                String folder = "src"+separator+"main"+separator+"resources"+separator+"static";
                List<String> pathsHashed = new ArrayList<>();

                Arrays.stream(files).forEach(file -> {
                    try {
                        String[] fileNameSeparated = file.getOriginalFilename().split("[.]");
                        String hashedName = UUID.randomUUID()+"."+fileNameSeparated[1];
                        ImgUtil.sacuvajFile(file, folder,hashedName);
                        pathsHashed.add(Paths.get(folder+separator+hashedName).toAbsolutePath().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                knjiga.setFotografijaPrednja(pathsHashed.get(0));
                knjiga.setFotografijaZadnja(pathsHashed.get(1));
            }
            knjigaRepo.save(knjiga);
            knjigeAutoraService.zapisiAutoreKnjiga(knjiga.getAutori());
            return true;
        }
        return false;
    }

    public List<Knjiga> sveKnjige(){
       return knjigaRepo.findAll();
    }

    public List<Knjiga> knjigePoNaslovu(String naslov){
        return knjigaRepo.findKnjigasByNaslovContains(naslov);
    }

    public List<Knjiga> knjigePoAutoru(Integer id){
        return knjigeAutoraService.knjigeAutora(autorRepo.findById(id).get());
    }

    public List<Knjiga> knjigePoKategoriji(Integer id){
        return knjigaRepo.findKnjigasByKategorija(kategorijaService.kategorijaPoId(id));
    }

    public Knjiga knjigaPoID(Integer id){
        return knjigaRepo.findById(id).get();
    }

    public Knjiga izmeniKnjigu(Knjiga knjiga){
        knjigaRepo.save(knjiga);
        knjigeAutoraService.izbrisiVezu(knjiga);
        knjigeAutoraService.zapisiAutoreKnjiga(knjiga.getAutori());
        return knjiga;
    }

    public List<Knjiga> knjigeNaPolici(Integer id){
        return knjigaRepo.findKnjigasByPolica(policaService.policaPoID(id));
    }

    public List<Knjiga> knjigePoIzdavacu(Integer izdavac){
        return knjigaRepo.findKnjigasByIzdavac(izdavacService.izdavacPoID(izdavac));
    }

    @Transactional
    public boolean izbrisiKnjigu(Integer id) {
        try {
            Knjiga knjiga = knjigaRepo.findById(id).get();
            knjigeAutoraService.izbrisiVezu(knjiga);
            ImgUtil.izbrisiFile(knjiga.getFotografijaPrednja());
            ImgUtil.izbrisiFile(knjiga.getFotografijaZadnja());
            knjigaRepo.deleteById(id);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
