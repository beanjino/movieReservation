package com.example.moviereservation.service;

import com.example.moviereservation.dto.MovieDto;
import com.example.moviereservation.entity.Movie;
import com.example.moviereservation.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
}
