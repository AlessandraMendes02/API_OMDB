package com.example.aos.omdb.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aos.omdb.demo.exception.FilmNotFoundException;
import com.example.aos.omdb.demo.model.Film;
import com.example.aos.omdb.demo.service.FilmService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/film/")
@RestController
@AllArgsConstructor
public class FilmController {
    
    @Autowired
    public FilmService filmService;

    @GetMapping("/sincronizar")
    public ResponseEntity<String> sincronizarDirectors() {
        String url = "\"http://www.omdbapi.com/?apikey=10891e28"; 
        filmService.consumirApiExternaeSalvar(url);
        return ResponseEntity.ok("Dados sincronizados com sucesso!");
    }


    @GetMapping
    public String getAllData(){
        return filmService.getAllData();
    }

    @GetMapping("all/")
    public List<Film> getFilms(){
        return filmService.findAll();
    }

    @GetMapping("{id}/")
    public Film getFilmById (@PathVariable ("id") Long id){
        try {
            return filmService.findById(id);
        } catch (FilmNotFoundException e) {
            return null;
        }
    }

    @PostMapping(value = "add/")
    public void insertFilm(@RequestBody Film film){
        filmService.insertFilm(film);
    }

    @PutMapping("update/{id}/")
    public void updateFilm (@PathVariable ("id") Long id, @RequestBody String title){
        try {
            filmService.updateFilm(id, title);
        } catch (FilmNotFoundException e) {
 
        }
    }

    @DeleteMapping("{id}/")
    public void deleteUser(@PathVariable Long id){
        try {
            filmService.deleteById(id);
        } catch (FilmNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getFilmByTitleAndGener(@RequestParam String title, @RequestParam String gener) {
        try {
            Film film = filmService.findByTitleAndGener(title, gener);
            return new ResponseEntity<>(film, HttpStatus.OK); 
        } catch (FilmNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}

