package com.volonter.Bibilioteka.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "knjiga_has_autor")
public class AutoriKnjiga {

    @Id
    @Column(name = "knjiga_has_autor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "knjiga_knjiga_id")
    private Knjiga knjigaAutora;

    @ManyToOne(optional = false)
    @JoinColumn(name = "autor_autor_id")
    private Autor autorKnjige;

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

    public Autor getAutorKnjige() {
        return autorKnjige;
    }

    public void setAutorKnjige(Autor autorKnjige) {
        this.autorKnjige = autorKnjige;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoriKnjiga that = (AutoriKnjiga) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getKnjigaAutora(), that.getKnjigaAutora()) && Objects.equals(getAutorKnjige(), that.getAutorKnjige());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getKnjigaAutora(), getAutorKnjige());
    }
}
