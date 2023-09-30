package com.example.moviereservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class MovieTheaterDto {
    private Long id;
    private Long theaterId;
    private Integer floor;
    private String screeningMovie;
    private Integer entireSeat;
    private LocalDate date;
    private String number;
}
