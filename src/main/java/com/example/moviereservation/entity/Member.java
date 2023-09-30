package com.example.moviereservation.entity;

import com.example.moviereservation.dto.CommentDto;
import com.example.moviereservation.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    @ColumnDefault("false")
    private Boolean isAdmin;

    public void patch(MemberDto dto) {
        // 예외 발생
        // 객체 갱신
        if(dto.getPassword() != null){
            this.password = dto.getPassword();
            this.name= dto.getName();
        }

    }
}
