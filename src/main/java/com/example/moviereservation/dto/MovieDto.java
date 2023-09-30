package com.example.moviereservation.dto;

import com.example.moviereservation.entity.Member;
import com.example.moviereservation.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@ToString
public class MovieDto {
    private Long id;
    private String name;
    private String runningtime;
    private Integer price;
    private String genre;
    private String director;
    private String actors;
    private Double rating;
    private String age;

    public Movie toEntity(){
        return new Movie(id, name, runningtime, price, genre, director, actors, rating, age);
    }
}
