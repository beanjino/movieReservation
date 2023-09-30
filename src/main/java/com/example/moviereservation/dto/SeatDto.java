package com.example.moviereservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@AllArgsConstructor
@ToString
@Setter
public class SeatDto {
    private Long id;
    private Long startTimeId;
    private String seatNumber;
    private String reserveMember;
}
