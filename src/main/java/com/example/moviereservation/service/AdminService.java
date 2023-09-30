package com.example.moviereservation.service;

import com.example.moviereservation.dto.MemberDto;
import com.example.moviereservation.dto.PageDto;
import com.example.moviereservation.entity.Member;
import com.example.moviereservation.entity.Movie;
import com.example.moviereservation.entity.Theater;
import com.example.moviereservation.repository.MemberRepository;
import com.example.moviereservation.repository.MovieRepository;
import com.example.moviereservation.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MemberRepository memberRepository;
    public PageDto pageFunc(int page, int boardCount){
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        if(boardCount==0) {
            maxPage=1;
        }
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        PageDto pageDTO = new PageDto();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }
    public List<Member> pagingListMember(int page) {
        /*
        1페이지당 보여지는 글 수 3개
            1페이지를 열기위해선 0이 필요
            2페이지는 3
            3페이지는 6
         */
        int pageStart = (page-1) * pageLimit;
        List<Member> pagingList = memberRepository.pagingList(pageStart, pageLimit);
        return pagingList;
    }
    int pageLimit = 3;
    int blockLimit = 3;
    public PageDto pagingParam(int page) {
        // 전체 글 갯수 조회
        int boardCount = memberRepository.memberCount();
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        PageDto pageDto = pageFunc(page, boardCount);
        return pageDto;
    }
    public Member pagingListByMemberEmail(int page, String email) {
        int pageStart = (page-1) * pageLimit;
        Member pagingList = memberRepository.pagingListByEmail(pageStart, pageLimit, email);
        return pagingList;
    }
    public List<Member> pagingListByMemberName(int page, String name) {
        int pageStart = (page-1) * pageLimit;
        List<Member> pagingList = memberRepository.pagingListByName(pageStart, pageLimit, name);
        return pagingList;
    }
    public PageDto pagingParamByMemberEmail(int page, String email) {
        // 전체 글 갯수 조회
        int boardCount = memberRepository.memberCountByEmail(email);
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        PageDto pageDto = pageFunc(page, boardCount);
        return pageDto;
    }
    public PageDto pagingParamByMemberName(int page, String name) {
        // 전체 글 갯수 조회
        int boardCount = memberRepository.memberCountByName(name);
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        PageDto pageDto = pageFunc(page, boardCount);
        return pageDto;
    }
    public List<Movie> pagingListMovie(int page) {
        /*
        1페이지당 보여지는 글 수 3개
            1페이지를 열기위해선 0이 필요
            2페이지는 3
            3페이지는 6
         */
        int pageStart = (page-1) * pageLimit;
        List<Movie> pagingList = movieRepository.pagingList(pageStart, pageLimit);
        return pagingList;
    }
    public List<Movie> pagingListByMovieName(int page, String name) {
        int pageStart = (page-1) * pageLimit;
        List<Movie> pagingList = movieRepository.pagingListByName(pageStart, pageLimit, name);
        return pagingList;
    }
    public PageDto pagingParamByMovieName(int page, String name) {
        // 전체 글 갯수 조회
        int boardCount = movieRepository.movieCountByName(name);
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        PageDto pageDto = pageFunc(page, boardCount);
        return pageDto;
    }
    public List<Theater> pagingListTheater(int page) {
        /*
        1페이지당 보여지는 글 수 3개
            1페이지를 열기위해선 0이 필요
            2페이지는 3
            3페이지는 6
         */
        int pageStart = (page-1) * pageLimit;
        List<Theater> pagingList = theaterRepository.pagingList(pageStart, pageLimit);
        return pagingList;
    }
    public List<Theater> pagingListByRegion(int page, String region) {
        int pageStart = (page-1) * pageLimit;
        List<Theater> pagingList = theaterRepository.pagingListByRegion(pageStart, pageLimit, region);
        return pagingList;
    }
    public PageDto pagingParamByRegion(int page, String region) {
        // 전체 글 갯수 조회
        int boardCount = theaterRepository.theaterCountByRegion(region);
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        PageDto pageDto = pageFunc(page, boardCount);
        return pageDto;
    }
    public Theater pagingListByCity(int page, String city) {
        int pageStart = (page-1) * pageLimit;
        Theater pagingList = theaterRepository.pagingListByCity(pageStart, pageLimit, city);
        return pagingList;
    }
    public PageDto pagingParamByCity(int page, String city) {
        // 전체 글 갯수 조회
        int boardCount = theaterRepository.theaterCountByRegion(city);
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        PageDto pageDto = pageFunc(page, boardCount);
        return pageDto;
    }
}
