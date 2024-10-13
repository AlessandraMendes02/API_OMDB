package com.example.aos.omdb.demo.exception;

public class FilmNotFoundException extends Exception{
    
    public FilmNotFoundException(String message){
        super(message);
    }
}
