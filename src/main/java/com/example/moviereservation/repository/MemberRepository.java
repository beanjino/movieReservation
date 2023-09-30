package com.example.moviereservation.repository;

import com.example.moviereservation.dto.MemberDto;
import com.example.moviereservation.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberRepository extends CrudRepository<Member, Long> {
    @Query(value = "SELECT * FROM member WHERE email = :memberEmail and password = :memberPassword",nativeQuery = true)
    Member findMember(String memberEmail, String memberPassword);

    @Query(value = "SELECT * FROM member WHERE id = :memberId",nativeQuery = true)
    Member findByMemberId(Long memberId);

    @Query(value = "SELECT * FROM member order by id limit :pageStart,:pageLimit",nativeQuery = true)
    List<Member> pagingList(Integer pageStart, Integer pageLimit);

    @Query(value = "SELECT * FROM member where name = :name order by id limit :pageStart,:pageLimit",nativeQuery = true)
    List<Member> pagingListByName(Integer pageStart, Integer pageLimit, String name);

    @Query(value = "SELECT * FROM member where email = :email order by id limit :pageStart,:pageLimit",nativeQuery = true)
    Member pagingListByEmail(Integer pageStart, Integer pageLimit, String email);

    @Query(value = "SELECT count(id) from member",nativeQuery = true)
    Integer memberCount();

    @Query(value = "SELECT count(id) from member where name = :name",nativeQuery = true)
    Integer memberCountByName(String name);

    @Query(value = "SELECT count(id) from member where email = :email",nativeQuery = true)
    Integer memberCountByEmail(String email);

    @Query(value = "SELECT * FROM member WHERE name = :name",nativeQuery = true)
    List<Member> findByname(String name);

    @Query(value = "SELECT * FROM member WHERE email = :email",nativeQuery = true)
    Member findByEmail(String email);
}
