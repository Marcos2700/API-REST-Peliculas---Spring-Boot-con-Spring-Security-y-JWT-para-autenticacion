package com.example.springsecuritycifrado.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String titulo;
    String director;
    String productora;
    LocalDate estreno;
    Boolean disponible;

    public Movie() {
    }

    public Movie(String titulo, String director, String productora, LocalDate estreno, Boolean disponible) {
        this.titulo = titulo;
        this.director = director;
        this.productora = productora;
        this.estreno = estreno;
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public LocalDate getEstreno() {
        return estreno;
    }

    public void setEstreno(LocalDate estreno) {
        this.estreno = estreno;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", director='" + director + '\'' +
                ", productora='" + productora + '\'' +
                ", estreno=" + estreno +
                ", disponible=" + disponible +
                '}';
    }
}
