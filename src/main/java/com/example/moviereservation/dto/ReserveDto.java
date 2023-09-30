package com.example.moviereservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReserveDto {
    private Long id;
    private Long memberId;
    private String movieName;
    private String city;
    private LocalDate date;
    private String time;
    private String number;
    private String seatNumber;
}
