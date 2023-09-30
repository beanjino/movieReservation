package com.example.moviereservation.repository;

import com.example.moviereservation.entity.MovieTheater;
import com.example.moviereservation.entity.Seat;
import com.example.moviereservation.entity.StartTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeatRepository extends CrudRepository<Seat, Long> {
    @Query(value = "SELECT * FROM seat WHERE start_time_id = :startTimeId",nativeQuery = true)
    List<Seat> findByStartTimeId(Long startTimeId);

    @Override
    List<Seat> findAll();
}
