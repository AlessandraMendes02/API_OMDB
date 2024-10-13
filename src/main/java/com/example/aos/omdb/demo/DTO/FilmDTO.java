package com.example.aos.omdb.demo.DTO;

import java.util.List;

public class FilmDTO {
    private Long id;
    private String title;
    private String year;
    private String imdbRating;
    private String gener;
    private List<Long> listaDeDiretoresIds; 


    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getGener() {
        return gener;
    }

    public List<Long> getListaDeDiretoresIds() {
        return listaDeDiretoresIds;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setGener(String gener) {
        this.gener = gener;
    }

    public void setListaDeDiretoresIds(List<Long> listaDeDiretoresIds) {
        this.listaDeDiretoresIds = listaDeDiretoresIds;
    }
}

