package com.example.moviereservation.entity;

import com.example.moviereservation.dto.CommentDto;
import com.example.moviereservation.dto.ReserveDto;
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
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Column
    private String movieName;
    @Column
    private String city;
    @Column
    private LocalDate date;
    @Column
    private String time;
    @Column
    private String number;
    @Column
    private String seatNumber;

    public static Reserve createReserve(ReserveDto dto, Member member) {
        // 예외 발생
        // 엔티티 생성 및 반환
        return new Reserve(
                dto.getId(),
                member,
                dto.getMovieName(),
                dto.getCity(),
                dto.getDate(),
                dto.getTime(),
                dto.getNumber(),
                dto.getSeatNumber()
        );
    }
}
