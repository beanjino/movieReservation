package com.example.moviereservation.repository;

import com.example.moviereservation.entity.Member;
import com.example.moviereservation.entity.Movie;
import com.example.moviereservation.entity.Theater;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TheaterRepository extends CrudRepository<Theater, Long> {
    @Override
    List<Theater> findAll();

    @Query(value = "SELECT * FROM theater WHERE city = :theaterCity",nativeQuery = true)
    Theater findByCity(String theaterCity);

    @Query(value = "SELECT * FROM theater where city = :city order by id limit :pageStart,:pageLimit",nativeQuery = true)
    Theater pagingListByCity(Integer pageStart, Integer pageLimit, String city);

    @Query(value = "SELECT count(id) from theater where city = :city",nativeQuery = true)
    Integer theaterCountBycity(String city);

    @Query(value = "SELECT * FROM theater where region = :region order by id limit :pageStart,:pageLimit",nativeQuery = true)
    List<Theater> pagingListByRegion(Integer pageStart, Integer pageLimit, String region);

    @Query(value = "SELECT count(id) from theater where region = :region",nativeQuery = true)
    Integer theaterCountByRegion(String region);

    @Query(value = "SELECT * FROM theater order by id limit :pageStart,:pageLimit",nativeQuery = true)
    List<Theater> pagingList(Integer pageStart, Integer pageLimit);
}
