package com.example.moviereservation.dto;

import com.example.moviereservation.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class MemberDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private Boolean isAdmin;

    public Member toEntity(){
        return new Member(id, email, password, name, false);
    }
}
