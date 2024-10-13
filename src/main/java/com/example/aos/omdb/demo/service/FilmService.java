package com.example.aos.omdb.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.aos.omdb.demo.DTO.FilmDTO;
import com.example.aos.omdb.demo.exception.FilmNotFoundException;
import com.example.aos.omdb.demo.model.Film;
import com.example.aos.omdb.demo.repository.FilmRepository;

@Service
public class FilmService {
    
    @Autowired
    private FilmRepository filmRepository;
 
    private final String url = "http://www.omdbapi.com/?apikey=10891e28"; 

    @Autowired 
    private RestTemplate restTemplate;

      public void consumirApiExternaeSalvar(String url) {
        ResponseEntity<FilmDTO[]> response = restTemplate.getForEntity(url, FilmDTO[].class);
        FilmDTO[] films = response.getBody();

        if (films != null) {
            for (FilmDTO filmDTO : films) {
                Film film = new Film();
                film.setTitle(filmDTO.getTitle());
                film.setYear(filmDTO.getYear());
                film.setImdbRating(filmDTO.getImdbRating());
                film.setGener(filmDTO.getGener());
                // Adicione lógica para associar diretores, se necessário

                filmRepository.save(film);  // Salva no banco
            }
        }
    }

    public String getAllData(){
      return restTemplate.getForObject(this.url, String.class);
    }

    public void insertFilm (Film film){
        filmRepository.save(film);
    }

    public void deleteById(Long id) throws FilmNotFoundException {
        Optional<Film> opFilm = filmRepository.findById(id);

        if(opFilm.isEmpty()){
            throw new FilmNotFoundException("Film not found!");
        }

        Film film = opFilm.get();
        filmRepository.delete(film);
        
    }

    public List<Film> findAll(){
        return filmRepository.findAll();
    }

    public Film findById (Long id) throws FilmNotFoundException{
        Optional<Film> opFilm = filmRepository.findById(id);
 
        if(opFilm.isEmpty()){
            throw new FilmNotFoundException("Film not found!");
        }
        return opFilm.get();
    }

    public void updateFilm(Long id, String title ) throws FilmNotFoundException{
        Optional<Film> opFilm = filmRepository.findById(id);
 
        if (opFilm.isEmpty()) {
            throw new FilmNotFoundException("Film not found!");
        }
 
        Film film = opFilm.get();
        film.setTitle(title);
        filmRepository.save(film);
    }

    public Film findByTitleAndGener(String title, String gener) throws FilmNotFoundException {
        Film filmGener = filmRepository.findByTitleAndGener(title, gener);
        if(filmGener != null){
            return filmGener;
        }
        throw new FilmNotFoundException("User not found");
    }
}
