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

import com.example.aos.omdb.demo.exception.DirectorNotFoundException;
import com.example.aos.omdb.demo.model.Director;
import com.example.aos.omdb.demo.service.DirectorService;

import lombok.AllArgsConstructor;



@RequestMapping("api/v1/director/")
@RestController
@AllArgsConstructor
public class DirectorController {
    
    @Autowired
    public DirectorService directorService;


    @GetMapping("/sincronizar")
    public ResponseEntity<String> sincronizarDirectors() {
        String url = "\"http://localhost:8080/api/director"; http://www.omdbapi.com/
        directorService.consumirApiExternaeSalvar(url);
        return ResponseEntity.ok("Dados sincronizados com sucesso!");
    }

    @PostMapping
    public Director insertDirector(@RequestBody Director director) {
        directorService.insertDirector(director);
        return director;
    }
    
    @GetMapping("all/")
    public List<Director> getDirectors(){
        return directorService.findAll();
    }
    
    @GetMapping("{id}/")
    public Director getDirectorById(@PathVariable ("id") Long id){
        try{
            return directorService.findById(id);
        } catch (DirectorNotFoundException e){
            return null;
        }
    }

    @PutMapping("update/{id}/")
    public void updateDirector (@PathVariable ("id") Long id, @RequestBody String name){
        try {
            directorService.updateDirector(id, name);
        } catch (DirectorNotFoundException e) {
        }}

    @DeleteMapping("{id}/")
    public void deleteDirector(@PathVariable Long id){
        try{
            directorService.deleteById(id);
        } catch (DirectorNotFoundException e) {
            throw new RuntimeException(e.getMessage());
    
    }
}

    @GetMapping("/search")
    public ResponseEntity<?> getDirectorByName(@RequestParam String name) {
        try{
            Director director = directorService.findByName(name);
            return new ResponseEntity<>(director, HttpStatus.OK);
        } catch (DirectorNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
              }
      }

   }


