package com.example.moviereservation.repository;

import com.example.moviereservation.entity.Member;
import com.example.moviereservation.entity.MovieTheater;
import com.example.moviereservation.entity.StartTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StartTimeRepository extends CrudRepository<StartTime, Long> {
    @Query(value = "SELECT * FROM start_time WHERE movie_theater_id = :movieTheaterId",nativeQuery = true)
    List<StartTime> findByMovieTheaterId(Long movieTheaterId);
}
