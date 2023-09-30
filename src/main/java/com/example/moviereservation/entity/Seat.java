package com.example.moviereservation.entity;

import com.example.moviereservation.dto.CommentDto;
import com.example.moviereservation.dto.ReserveDto;
import com.example.moviereservation.dto.SeatDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Seat{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "StartTime_id")
    private StartTime startTime;
    @Column
    private String seatNumber;
    @Column
    private String reserveMember;

    public static Seat createSeat(SeatDto dto, StartTime startTime) {
        // 예외 발생
        // 엔티티 생성 및 반환
        return new Seat(
                dto.getId(),
                startTime,
                dto.getSeatNumber(),
                dto.getReserveMember()
        );
    }
}
