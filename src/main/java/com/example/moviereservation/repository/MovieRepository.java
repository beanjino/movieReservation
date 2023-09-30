package com.example.moviereservation.repository;

import com.example.moviereservation.entity.Member;
import com.example.moviereservation.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    @Override
    List<Movie> findAll();

    @Query(value = "SELECT * FROM movie WHERE name = :name",nativeQuery = true)
    Movie findByMovieName(String name);

    @Query(value = "SELECT * FROM movie where name = :name order by id limit :pageStart,:pageLimit",nativeQuery = true)
    List<Movie> pagingListByName(Integer pageStart, Integer pageLimit, String name);

    @Query(value = "SELECT count(id) from movie where name = :name",nativeQuery = true)
    Integer movieCountByName(String name);

    @Query(value = "SELECT * FROM movie order by id limit :pageStart,:pageLimit",nativeQuery = true)
    List<Movie> pagingList(Integer pageStart, Integer pageLimit);
}
