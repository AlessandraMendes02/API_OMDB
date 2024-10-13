package com.example.aos.omdb.demo.DTO;

public class DirectorDTO {
    private Long id;
    private String director;
    private String name;
    private int age;
    private String nacionality;

    // Getters
    public Long getId() {
        return id;
    }

    public String getDirector() {
        return director;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNacionality() {
        return nacionality;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }
}
 

