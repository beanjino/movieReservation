package com.example.moviereservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDto {
    private int page;
    private int maxPage;
    private int startPage;
    private int endPage;
}
