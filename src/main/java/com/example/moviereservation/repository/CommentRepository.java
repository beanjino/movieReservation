package com.example.moviereservation.repository;

import com.example.moviereservation.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comment WHERE movie_id = :movieId", nativeQuery = true)
    List<Comment> findByMovieId(Long movieId);
}
