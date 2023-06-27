package com.volonter.Bibilioteka.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "knjiga_has_autor")
public class AutoriKnjiga {

    @Id
    @Column(name = "knjiga_has_autor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "knjiga_knjiga_id")
    private Knjiga knjigaAutora;

    @ManyToOne(optional = false)
    @JsonProperty("autorKnjige")
    @JoinColumn(name = "autor_autor_id")
    @JsonBackReference
    private Autor autorKnjige;

    public AutoriKnjiga(Autor autorKnjige) {
        this.autorKnjige = autorKnjige;
    }

    public AutoriKnjiga() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Knjiga getKnjigaAutora() {
        return knjigaAutora;
    }

    public void setKnjigaAutora(Knjiga knjigaAutora) {
        this.knjigaAutora = knjigaAutora;
    }

    public Autor getAutoriKnjige() {
        return autorKnjige;
    }

    public void setAutoriKnjige(Autor autoriKnjige) {
        this.autorKnjige = autoriKnjige;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoriKnjiga that = (AutoriKnjiga) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getKnjigaAutora(), that.getKnjigaAutora()) && Objects.equals(getAutoriKnjige(), that.getAutoriKnjige());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getKnjigaAutora(), getAutoriKnjige());
    }
}
