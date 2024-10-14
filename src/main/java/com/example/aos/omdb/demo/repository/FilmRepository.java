package com.example.aos.omdb.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aos.omdb.demo.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {

    Film findByTitleAndGener(String title, String gener);

}

