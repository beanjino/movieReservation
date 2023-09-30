package com.example.moviereservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String runningtime;
    @Column
    private Integer price;
    @Column
    private String genre;
    @Column
    private String director;
    @Column
    private String actors;
    @Column
    private Double rating;
    @Column
    private String age;
}
