package com.example.moviereservation.dto;

import com.example.moviereservation.entity.Member;
import com.example.moviereservation.entity.Theater;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class TheaterDto {
    private Long id;
    private String city;// 포항
    private String region;//경상북도

    public Theater toEntity(){
        return new Theater(id, city, region);
    }
}
