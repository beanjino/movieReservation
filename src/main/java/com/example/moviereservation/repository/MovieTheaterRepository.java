package com.example.moviereservation.repository;

import com.example.moviereservation.entity.Member;
import com.example.moviereservation.entity.Movie;
import com.example.moviereservation.entity.MovieTheater;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovieTheaterRepository extends CrudRepository<MovieTheater, Long> {
    @Query(value = "SELECT * FROM movie_theater WHERE screening_movie = :movie and theater_id = :theaterId and date = :date",nativeQuery = true)
    List<MovieTheater> finddetail(String movie, Long theaterId, LocalDate date);
}
