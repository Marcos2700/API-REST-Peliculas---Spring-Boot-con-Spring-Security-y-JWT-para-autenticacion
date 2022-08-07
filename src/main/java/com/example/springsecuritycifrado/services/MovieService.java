package com.example.springsecuritycifrado.services;

import com.example.springsecuritycifrado.entities.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAll();

    Optional<Movie> findById(Long id);

    Movie addMovie(Movie movie);

    Movie update(Movie movie);

    void deleteById(Long id);

    void deleteAll();

    Movie setOffById(Long id);

    Optional<Movie> findByTitulo(String titulo);

    List<Movie> findByEstreno(String date);

    List<Movie> findByDirector(String director);

}
