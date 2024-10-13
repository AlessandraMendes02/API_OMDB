package com.example.aos.omdb.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Director {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String director;

    @NonNull
    private String name;

    @ManyToMany(mappedBy = "listaDeDiretores")
    @JsonIgnore
    private List<Film> listaDeFilmes; 


    @NonNull
    private int age;

    @NonNull
    private String nacionality;

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Director get() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
