package com.volonter.Bibilioteka.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity(name = "kategorija")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",scope = Kategorija.class)
public class Kategorija {

    @Id
    @Column(name = "kategorija_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv",nullable = false)
    @JsonProperty("naziv")
    private String naziv;

    @JsonIgnore
    @OneToMany(mappedBy = "kategorija",cascade = CascadeType.ALL)
    Set<Knjiga> knjige;

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
        Kategorija that = (Kategorija) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNaziv(), that.getNaziv());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNaziv());
    }
}
