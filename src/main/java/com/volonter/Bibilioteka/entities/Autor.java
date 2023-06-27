package com.volonter.Bibilioteka.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity(name = "autor")
public class Autor {

    @Id
    @Column(name = "autor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ime",nullable = false)
    private String ime;

    @Column(name = "prezime",nullable = false)
    private String prezime;

    @JsonIgnore
    @OneToMany(mappedBy = "autorKnjige",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<AutoriKnjiga> knjige;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Set<AutoriKnjiga> getKnjige() {
        return knjige;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(getId(), autor.getId()) && Objects.equals(getIme(), autor.getIme()) && Objects.equals(getPrezime(), autor.getPrezime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIme(), getPrezime());
    }


}
