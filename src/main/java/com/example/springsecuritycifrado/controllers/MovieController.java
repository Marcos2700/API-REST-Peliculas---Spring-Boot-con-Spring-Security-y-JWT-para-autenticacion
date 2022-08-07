package com.example.springsecuritycifrado.controllers;

import com.example.springsecuritycifrado.entities.Movie;
import com.example.springsecuritycifrado.repositories.MoviesRepository;
import com.example.springsecuritycifrado.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieController {

    private MovieService movieService;

    private Logger log = LoggerFactory.getLogger(MovieController.class);

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> findAll(){
        log.info("Finding all movies.");
        return movieService.findAll();
    }

    @GetMapping("/movies/byId/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id){
        Optional<Movie> movie = movieService.findById(id);
        if(!movie.isPresent()){
            log.warn("Movie not found");
            return ResponseEntity.notFound().build();
        }
        log.info("Returning movie");
        return ResponseEntity.ok(movie.get());
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        if(movie.getId() != null){
            log.warn("You are trying to update a movie: id number does exist");
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(movieService.addMovie(movie));
    }

    @PutMapping("/movies")
    public ResponseEntity<Movie> update(@RequestBody Movie movie){
        if(movie.getId() == null){
            log.warn("You are trying to create a new movie: set id of a movie that exist");
            return ResponseEntity.badRequest().build();
        }
        log.info("Movie updated");
        return ResponseEntity.ok(movieService.update(movie));
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Movie> deleteById(@PathVariable Long id){
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/movies")
    public ResponseEntity<Movie> deleteAll(){
        movieService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/movies/setOff/{id}")
    public ResponseEntity<Movie> setOff(@PathVariable Long id){
        Movie movie = movieService.setOffById(id);
        if(movie == null){
            log.warn("Movie does not exist");
            return ResponseEntity.notFound().build();
        }
        log.info("Movie turned off");
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/movies/byTitulo/{titulo}")
    public ResponseEntity<Movie> findById(@PathVariable String titulo){
        Optional<Movie> movie = movieService.findByTitulo(titulo);
        if(!movie.isPresent()){
            log.warn("Movie not found");
            return ResponseEntity.notFound().build();
        }
        log.info("Returning movie");
        return ResponseEntity.ok(movie.get());
    }

    @GetMapping("/movies/byEstreno/{date}")
    public List<Movie> findByEstreno(@PathVariable String date){
        return movieService.findByEstreno(date);
    }

    @GetMapping("/movies/byDirector/{director}")
    public List<Movie> findByDirector(@PathVariable String director){
        return movieService.findByDirector(director);
    }

}
