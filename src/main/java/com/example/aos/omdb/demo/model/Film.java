package com.example.aos.omdb.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Film {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String year;

    @ManyToMany
    @JoinTable(
        name = "director",
        joinColumns = @JoinColumn(name = "director_id"),
        inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    @JsonIgnore
    private List<Director> listaDeDiretores; 

    @NonNull
    private String imdbRating;

    @NonNull
    private String genre;
}
