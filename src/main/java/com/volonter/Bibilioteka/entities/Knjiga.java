package com.volonter.Bibilioteka.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "knjiga")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",scope = Knjiga.class)
public class Knjiga {

    @Id
    @Column(name = "knjiga_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naslov",nullable = false)
    private String naslov;

    @Column(name = "originalni_naslov")
    private String originalniNaslov;

    @Column(name = "godina_stampe",nullable = false)
    private Integer godinaStampe;

    @Column(name = "broj_strana",nullable = false)
    private Integer brojStrana;

    @Column(name = "id_stampane_publikacije")
    private Integer idStampanePublikacije;

    @Column(name = "jezik_sadrzaja",nullable = false)
    private String jezikSadrzaja;

    @Column(name = "fotografija_prednja")
    private String fotografijaPrednja;

    @Column(name = "fotografija_zadnja")
    private String fotografijaZadnja;

//    
    @JsonProperty("izdavac")
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "izdavac_izdavac_id",nullable = false)
    private Izdavac izdavac;

    /**/
    @JsonProperty("kategorija")
    
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "kategorija_kategorija_id",nullable = false)
    private Kategorija kategorija;
    
    @JsonProperty("polica")
    @ManyToOne(optional = false)
    @JoinColumn(name = "polica_polica_id",nullable = false)
    private Polica polica;

    @JsonProperty("autori")
    @JsonManagedReference(value = "autori-knjige")
    @OneToMany(mappedBy = "knjigaAutora",cascade = CascadeType.REMOVE)
    private Set<AutoriKnjiga> autori;

    public Integer getId() {
        return id;
    }

    public void setAutori(Set<AutoriKnjiga> autori) {
        this.autori = autori;
    }

    public Knjiga() {
    }



    public void addAutori(AutoriKnjiga autor){
        autori.add(autor);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOriginalniNaslov() {
        return originalniNaslov;
    }

    public void setOriginalniNaslov(String originalniNaslov) {
        this.originalniNaslov = originalniNaslov;
    }

    public Integer getGodinaStampe() {
        return godinaStampe;
    }

    public void setGodinaStampe(Integer godinaStampe) {
        this.godinaStampe = godinaStampe;
    }

    public Integer getBrojStrana() {
        return brojStrana;
    }

    public void setBrojStrana(Integer brojStrana) {
        this.brojStrana = brojStrana;
    }

    public Integer getIdStampanePublikacije() {
        return idStampanePublikacije;
    }

    public void setIdStampanePublikacije(Integer idStampanePublikacije) {
        this.idStampanePublikacije = idStampanePublikacije;
    }

    public String getJezikSadrzaja() {
        return jezikSadrzaja;
    }

    public void setJezikSadrzaja(String jezikSadrzaja) {
        this.jezikSadrzaja = jezikSadrzaja;
    }

    public String getFotografijaPrednja() {
        return fotografijaPrednja;
    }

    public void setFotografijaPrednja(String fotografijaPrednja) {
        this.fotografijaPrednja = fotografijaPrednja;
    }

    public String getFotografijaZadnja() {
        return fotografijaZadnja;
    }

    public void setFotografijaZadnja(String fotografijaZadnja) {
        this.fotografijaZadnja = fotografijaZadnja;
    }

    public Izdavac getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(Izdavac izdavac) {
        this.izdavac = izdavac;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public Polica getPolica() {
        return polica;
    }

    public Set<AutoriKnjiga> getAutori() {
        return autori;
    }

    public void setPolica(Polica polica) {
        this.polica = polica;
    }
}
