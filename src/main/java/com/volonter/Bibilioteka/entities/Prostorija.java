package com.volonter.Bibilioteka.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity(name = "prostorija")
public class Prostorija {

    @Id
    @Column(name = "prostorija_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv",nullable = false)
    private String naziv;

    @OneToMany(mappedBy = "prostorija",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Polica> police;

    public Integer getId() {
        return id;
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

    public Set<Polica> getPolice() {
        return police;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prostorija that = (Prostorija) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNaziv(), that.getNaziv());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNaziv());
    }


}
