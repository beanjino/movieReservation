package com.example.moviereservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class MovieTheater{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
    @Column
    private Integer floor;
    @Column
    private String screeningMovie;
    @Column
    private Integer entireSeat;
    @Column
    private String number;
    @Column
    private LocalDate date;


}
