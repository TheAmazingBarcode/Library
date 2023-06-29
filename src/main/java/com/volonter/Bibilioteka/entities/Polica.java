package com.volonter.Bibilioteka.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "polica")
public class Polica {

    @Id
    @Column(name = "polica_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv",nullable = false)
    private String naziv;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "prostorija_prostorija_id",nullable = false)
    private Prostorija prostorija;

    @JsonBackReference(value = "polica-knjige")
    @OneToMany(mappedBy = "polica",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Knjiga> knjige;

    public Polica() {
    }

    public Prostorija getProstorija() {
        return prostorija;
    }

    public void setProstorija(Prostorija prostorija) {
        this.prostorija = prostorija;
    }

    public Integer getId() {
        return id;
    }

    public Set<Knjiga> getKnjige() {
        return knjige;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polica polica = (Polica) o;
        return Objects.equals(getId(), polica.getId()) && Objects.equals(getNaziv(), polica.getNaziv());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNaziv());
    }
}
