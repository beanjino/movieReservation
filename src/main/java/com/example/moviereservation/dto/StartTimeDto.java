package com.example.moviereservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
@Setter
public class StartTimeDto {
    private Long id;
    private String time;
    private Long movieTheaterId;
    private Integer leftSeat;
//    private LocalDate date;
}
