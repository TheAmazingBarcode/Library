package com.volonter.Bibilioteka.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity(name = "izdavac")
public class Izdavac {

    @Id
    @Column(name = "izdavac_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "grad")
    private String grad;

    @Column(name = "drzava")
    private String drzava;

    @Column(name = "godina_osnivanja")
    private Integer godinaOsnivanja;

    @JsonBackReference(value = "izdavac-knjige")
    @OneToMany(mappedBy = "izdavac",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Knjiga> knjige;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public Set<Knjiga> getKnjige() {
        return knjige;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public Integer getGodinaOsnivanja() {
        return godinaOsnivanja;
    }

    public void setGodinaOsnivanja(Integer godinaOsnivanja) {
        this.godinaOsnivanja = godinaOsnivanja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Izdavac izdavac = (Izdavac) o;
        return Objects.equals(getId(), izdavac.getId()) && Objects.equals(getNaziv(), izdavac.getNaziv()) && Objects.equals(getGrad(), izdavac.getGrad()) && Objects.equals(getDrzava(), izdavac.getDrzava()) && Objects.equals(getGodinaOsnivanja(), izdavac.getGodinaOsnivanja());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNaziv(), getGrad(), getDrzava(), getGodinaOsnivanja());
    }
}
