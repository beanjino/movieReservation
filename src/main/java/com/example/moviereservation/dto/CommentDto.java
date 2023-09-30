package com.example.moviereservation.dto;

import com.example.moviereservation.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    private Long movieId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getMovie().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
