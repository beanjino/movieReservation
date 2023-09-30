package com.example.moviereservation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class StartTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movieTheater_id")
    private MovieTheater movieTheater;
    @Column
    private String time;
    @Column
    private Integer leftSeat;
//    @Column
//    private LocalDate date;
}
