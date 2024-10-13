package com.example.aos.omdb.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.aos.omdb.demo.DTO.DirectorDTO;
import com.example.aos.omdb.demo.exception.DirectorNotFoundException;
import com.example.aos.omdb.demo.model.Director;
import com.example.aos.omdb.demo.repository.DirectorRepository;

@Service
public class DirectorService {
    
    @Autowired
    private DirectorRepository directorRepository;


    @Autowired
    private RestTemplate restTemplate;

    // Consumir API e salvar diretores no banco
    public void consumirApiExternaeSalvar(String url) {
        ResponseEntity<DirectorDTO[]> response = restTemplate.getForEntity(url, DirectorDTO[].class);
        DirectorDTO[] directors = response.getBody();

        if (directors != null) {
            for (DirectorDTO directorDTO : directors) {
                Director director = new Director();
                director.setDirector(directorDTO.getDirector());
                director.setName(directorDTO.getName());
                director.setAge(directorDTO.getAge());
                director.setNacionality(directorDTO.getNacionality());
                // Adicione lógica para associar filmes, se necessário

                directorRepository.save(director);  // Salva no banco
            }
        }
    }

     public void insertDirector(Director director) {
       directorRepository.save(director);
    }

    public void deleteById(Long id) throws DirectorNotFoundException {
        Optional<Director> opDirector = directorRepository.findById(id);
        
        if(opDirector.isEmpty()){
            throw new DirectorNotFoundException("Director not found!");
        }
        
        Director director = opDirector.get();
        directorRepository.delete(director);
    }

    public Director findByName(String name) throws DirectorNotFoundException {
        Director opDirector = directorRepository.findByName(name);

        if (opDirector.isEmpty()) {
            throw new DirectorNotFoundException("Director not found!");
        }

        return opDirector.get();
    }


    public void deleteByName(String director) throws DirectorNotFoundException {
        Director opDirector = directorRepository.findByName(director);
        
        if (opDirector.isEmpty()) {
            throw new DirectorNotFoundException("Director not found!");
        }
    
        directorRepository.deleteByName(director);
    }
    

    public List<Director> findAll(){
        return directorRepository.findAll();
    }
    
    public Director findById(Long id) throws DirectorNotFoundException{
        Optional<Director> opDirector = directorRepository.findById(id);

        if(opDirector.isEmpty()){
            throw new DirectorNotFoundException("Director not found!");
        }
        return opDirector.get();
    }

    public void updateDirector(Long id, String name) throws DirectorNotFoundException{
        Optional<Director> opDirector = directorRepository.findById(id);

        if(opDirector.isEmpty()){
            throw new DirectorNotFoundException("Director not found!");
        }

        Director director = opDirector.get();
        director.setDirector(name);
        directorRepository.save(director);

    }

    public Director findByAge(int age) throws DirectorNotFoundException {

        Director opDirector = directorRepository.findByAge(age);
        
        if (opDirector.isEmpty()) {
            throw new DirectorNotFoundException("Director not found!");
        }
    
        return opDirector.get();
    }

    public Director findByNacionality(String nacionality) throws DirectorNotFoundException {

        Director opDirector = directorRepository.findByNacionality(nacionality);

        if(opDirector.isEmpty()){
            throw new DirectorNotFoundException("Director not found!");
        }
        
        return opDirector.get();
    }

    
}

