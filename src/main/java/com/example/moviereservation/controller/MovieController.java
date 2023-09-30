package com.example.moviereservation.controller;

import com.example.moviereservation.dto.CommentDto;
import com.example.moviereservation.dto.ReserveDto;
import com.example.moviereservation.dto.SeatDto;
import com.example.moviereservation.entity.*;
import com.example.moviereservation.repository.*;
import com.example.moviereservation.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MovieController {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private StartTimeRepository startTimeRepository;
    @Autowired
    private MovieTheaterRepository movieTheaterRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private MovieRepository movieRepository;
    @GetMapping("/movie")
    public String movie(Model model, HttpSession session){
        Member user = (Member) session.getAttribute("loginuser");
        model.addAttribute("loginuser",user);
        System.out.println("user = " + user);
        List<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "/movie/movies";
    }
    @GetMapping("/movie/{id}/detail")
    public String detail(@PathVariable Long id, Model model, HttpSession session){
        Member user = (Member) session.getAttribute("loginuser");
        model.addAttribute("loginuser",user);
        Movie movieEntity = movieRepository.findById(id).orElse(null);
        // 리뷰 불러오기
        List<CommentDto> comments = commentService.comments(id);
        model.addAttribute("movie", movieEntity);
        model.addAttribute("commentDtos", comments);
        return "/movie/detail";
    }
    @GetMapping("/reservation")
    public String reservationpage(Model model){
        List<Movie> movies = movieRepository.findAll();
        List<Theater> theaters = theaterRepository.findAll();
        model.addAttribute("movies", movies);
        model.addAttribute("theaters", theaters);
        return "/movie/reservation";
    }
    @GetMapping("/movie/{id}/reservation")
    public String reservationpage(@PathVariable Long id, Model model){
        Movie movieEntity = movieRepository.findById(id).orElse(null);
        model.addAttribute("movie", movieEntity);
        List<Movie> movies = movieRepository.findAll();
        List<Theater> theaters = theaterRepository.findAll();
        model.addAttribute("movies", movies);
        model.addAttribute("theaters", theaters);
        return "/movie/reservation";
    }
    @GetMapping("/reservedetail")
    public String redirectreservedetail(HttpSession session, Model model){
        Member user = (Member) session.getAttribute("loginuser");
        model.addAttribute("loginuser",user);
        List<MovieTheater> movieTheater = (List<MovieTheater>) session.getAttribute("movieTheater");
        LocalDate date = (LocalDate) session.getAttribute("date");
        List<StartTime> startTime = (List<StartTime>) session.getAttribute("startTime");
        session.removeAttribute("movieTheater");
        session.removeAttribute("date");
        session.removeAttribute("startTime");
        model.addAttribute("movieTheater", movieTheater);
        model.addAttribute("date", date);
        model.addAttribute("startTime", startTime);
        return "/movie/reservedetail";
    }
    @PostMapping("/reservedetail")
    public String reservedetail(@RequestParam String movie, @RequestParam String city, @RequestParam LocalDate date, Model model,
                                HttpSession session){
//        System.out.println("movie = " + movie);
//        System.out.println("city = " + city);
        Member user = (Member) session.getAttribute("loginuser");
        if(user!=null){
            model.addAttribute("loginuser",user);
        }
        Theater theaterEntity = theaterRepository.findByCity(city);
        List<MovieTheater> movieTheater = movieTheaterRepository.finddetail(movie,theaterEntity.getId(),date);
        model.addAttribute("movieTheater", movieTheater);
        model.addAttribute("date", date);
        List<StartTime> startTime = new ArrayList<>();
        for (int i = 0; i < movieTheater.size(); i++) {
            List<StartTime> st = startTimeRepository.findByMovieTheaterId(movieTheater.get(i).getId());
            startTime.addAll(st);
        }
        model.addAttribute("startTime", startTime);
        session.setAttribute("movieTheater", movieTheater);
        session.setAttribute("date", date);
        session.setAttribute("startTime", startTime);
        return "/movie/reservedetail";
    }
    @PostMapping("/reserveseat")
    public String reserverseat(@RequestParam LocalDate date, @RequestParam String time, @RequestParam String city,
                               @RequestParam String movie, @RequestParam String number, @RequestParam Integer entireSeat,
                               Model model, @RequestParam Long startTimeId, HttpSession session ){
        Movie reserveMovie = movieRepository.findByMovieName(movie);
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("city", city);
        model.addAttribute("number", number);
        model.addAttribute("movie", reserveMovie);
        model.addAttribute("entireSeat", entireSeat);
        model.addAttribute("startTimeId", startTimeId);
        Member user = (Member) session.getAttribute("loginuser");
        model.addAttribute("loginuser",user);
        List<Seat> seat = seatRepository.findAll();
        model.addAttribute("reservedSeat",seat);
        System.out.println("seat = " + seat);
        return "/movie/seat";
    }
    @PostMapping("/reserveSeat")
    public ResponseEntity<String> reserveSeat(@RequestBody List<SeatDto> selectedSeat, HttpSession session){
        System.out.println("selectedSeat = " + selectedSeat);
        for(SeatDto seatDto : selectedSeat){
            System.out.println("seatDto = " + seatDto);
            StartTime startTime = startTimeRepository.findById(seatDto.getStartTimeId()).orElse(null);
            startTime.setLeftSeat(startTime.getLeftSeat()-1);
            startTimeRepository.save(startTime);
            Seat seat = Seat.createSeat(seatDto, startTime);
            seatRepository.save(seat);
        }
        return ResponseEntity.ok("Data received successfully");
    }
    @PostMapping("/reserve")
    public ResponseEntity<String> reserve(@RequestBody ReserveDto reserveDto){
        System.out.println("reserveDto = " + reserveDto);
        Member member = memberRepository.findByMemberId(reserveDto.getMemberId());
        Reserve reserve = Reserve.createReserve(reserveDto,member);
        reserveRepository.save(reserve);
        return ResponseEntity.ok("Data received successfully");
    }
}
