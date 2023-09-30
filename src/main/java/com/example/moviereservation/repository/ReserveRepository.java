package com.example.moviereservation.repository;

import com.example.moviereservation.dto.ReserveDto;
import com.example.moviereservation.entity.Movie;
import com.example.moviereservation.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReserveRepository extends CrudRepository<Reserve, Long> {
    @Query(value = "SELECT * FROM reserve WHERE member_id = :memberId",nativeQuery = true)
    List<Reserve> findByMemberId(Long memberId);
}
