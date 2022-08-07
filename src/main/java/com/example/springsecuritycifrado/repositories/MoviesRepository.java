package com.example.springsecuritycifrado.repositories;

import com.example.springsecuritycifrado.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitulo(String titulo);

    List<Movie> findByDirector(String director);

    List<Movie> findByEstreno(LocalDate date);
}
