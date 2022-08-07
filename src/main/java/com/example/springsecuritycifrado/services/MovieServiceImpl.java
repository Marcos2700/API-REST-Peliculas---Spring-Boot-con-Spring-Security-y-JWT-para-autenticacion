package com.example.springsecuritycifrado.services;

import com.example.springsecuritycifrado.entities.Movie;
import com.example.springsecuritycifrado.repositories.MoviesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Realiza las operaciones de servicios que necesita el controlador
 *
 */

@Service
public class MovieServiceImpl implements MovieService {

    private final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    private MoviesRepository repository;

    public MovieServiceImpl(MoviesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Movie> findAll() {
        log.info("Finding all movie in database");
        return this.repository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        log.info("Finding one movie in database");
        return this.repository.findById(id);
    }

    @Override
    public Movie addMovie(Movie movie) {
        log.info("Adding a movie in database");
        return this.repository.save(movie);
    }

    @Override
    public Movie update(Movie movie) {
        log.info("Updating movie in database");
        return this.repository.save(movie);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting one movie from database");
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.warn("Deleting all movies from database");
        this.repository.deleteAll();
    }

    @Override
    public Movie setOffById(Long id) {
        Optional<Movie> movie = this.repository.findById(id);
        if(!movie.isPresent()){
            log.warn("The movie that you are trying to set off does not exist");
            return movie.get();
        }
        log.info("Movie set off");
        movie.get().setDisponible(false);
        return this.repository.save(movie.get());
    }

    @Override
    public Optional<Movie> findByTitulo(String titulo) {
        log.info("Finding movie by titulo");
        return this.repository.findByTitulo(titulo);
    }

    @Override
    public List<Movie> findByEstreno(String date) {
        log.info("Finding movies by premiere date");
        LocalDate estreno = LocalDate.parse(date);
        return this.repository.findByEstreno(estreno);
    }

    @Override
    public List<Movie> findByDirector(String director) {
        log.info("Finding movies by director");
        return this.repository.findByDirector(director);
    }
}
