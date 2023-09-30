package com.example.moviereservation.controller;

import com.example.moviereservation.dto.MemberDto;
import com.example.moviereservation.dto.MovieDto;
import com.example.moviereservation.dto.PageDto;
import com.example.moviereservation.dto.TheaterDto;
import com.example.moviereservation.entity.Member;
import com.example.moviereservation.entity.Movie;
import com.example.moviereservation.entity.Theater;
import com.example.moviereservation.repository.MemberRepository;
import com.example.moviereservation.repository.MovieRepository;
import com.example.moviereservation.repository.TheaterRepository;
import com.example.moviereservation.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class adminController {
    @Autowired
    AdminService adminService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @GetMapping("/admin")
    public String adminPage(Model model, HttpSession session){
        Member user = (Member) session.getAttribute("loginuser");
        model.addAttribute("loginuser", user);
        return "/admin/admin";
    }
    @GetMapping("/adminCreateTheater")
    public String adminCreateTheaterPage(Model model, HttpSession session){
        Member user = (Member) session.getAttribute("loginuser");
        model.addAttribute("loginuser", user);
        return "/admin/adminCreateTheater";
    }
    @PostMapping("/adminCreateTheater")
    public String adminCreateTheater(@ModelAttribute TheaterDto theaterDto){
        Theater theater = theaterDto.toEntity();
        theaterRepository.save(theater);
        return "redirect:/adminCreateTheater";
    }
    @GetMapping("/adminCreateMember")
    public String adminCreateMemberPage(Model model, HttpSession session){
        Member user = (Member) session.getAttribute("loginuser");
        model.addAttribute("loginuser", user);
        return "/admin/adminCreateMember";
    }
    @PostMapping("/adminCreateMember")
    public String adminCreateMember(@ModelAttribute MemberDto memberDto){
        Member member = memberDto.toEntity();
        memberRepository.save(member);
        return "redirect:/adminCreateMember";
    }
    @GetMapping("/adminCreateMovie")
    public String adminCreateMoviePage(Model model, HttpSession session){
        Member user = (Member) session.getAttribute("loginuser");
        model.addAttribute("loginuser", user);
        return "/admin/adminCreateMovie";
    }
    @PostMapping("/adminCreateMovie")
    public String adminCreateMovie(@ModelAttribute MovieDto movieDto){
        Movie movie = movieDto.toEntity();
        movieRepository.save(movie);
        return "redirect:/adminCreateMovie";
    }
    @GetMapping("/adminManageMember")
    public String adminManageMember(Model model,  @RequestParam(value="page", required = false,defaultValue = "1")int page){
        System.out.println("page = " + page);
        // 해당 페이지에서 보여줄 글 목록
        List<Member> pagingList = adminService.pagingListMember(page);
        System.out.println("pagingList = " + pagingList);
        PageDto pageDTO = adminService.pagingParam(page);
        model.addAttribute("pagingList", pagingList);
        model.addAttribute("paging",pageDTO);
        return "/admin/adminManageMember";
    }
    @GetMapping("/adminUpdateMember")
    public String adminUpdateMemberPage(Model model, @RequestParam(value = "id") Long id){
        Member member = memberRepository.findById(id).orElse(null);
        if(member!=null){
            model.addAttribute("member", member);
        }
        return "/admin/adminUpdateMember";
    }
    @PostMapping("/adminUpdateMember")
    public String adminUpdateMember(@ModelAttribute MemberDto memberDto){
        Member member = memberDto.toEntity();
        memberRepository.save(member);
        return "redirect:/adminManageMember";
    }
    @PostMapping("/adminSearchMember")
    public String adminSearchMember(@RequestParam(name="email", required = false) String email,
                                    @RequestParam(name="name", required = false) String name,
                                    @RequestParam(value="page", required = false, defaultValue = "1")int page,
                                    Model model){
        if (email!=null) {
            Member searchData = adminService.pagingListByMemberEmail(page, email);
            if(searchData!=null){
                model.addAttribute("pagingList", searchData);
            }
            PageDto pageDTO = adminService.pagingParamByMemberEmail(page, email);
            model.addAttribute("paging",pageDTO);
        }
        else{
            List<Member> searchData = adminService.pagingListByMemberName(page, name);
            if(searchData!=null){
                model.addAttribute("pagingList", searchData);
            }
            PageDto pageDTO = adminService.pagingParamByMemberName(page, name);
            model.addAttribute("paging",pageDTO);
        }
        return "/admin/adminSearchMember";
    }
    @GetMapping("/adminManageMovie")
    public String adminManageMovie(Model model,  @RequestParam(value="page", required = false,defaultValue = "1")int page){
        System.out.println("page = " + page);
        // 해당 페이지에서 보여줄 글 목록
        List<Movie> pagingList = adminService.pagingListMovie(page);
        System.out.println("pagingList = " + pagingList);
        PageDto pageDTO = adminService.pagingParam(page);
        model.addAttribute("pagingList", pagingList);
        model.addAttribute("paging",pageDTO);
        return "/admin/adminManageMovie";
    }
    @GetMapping("/adminUpdateMovie")
    public String adminUpdateMoviePage(Model model, @RequestParam(value = "id") Long id){
        Movie movie = movieRepository.findById(id).orElse(null);
        if(movie!=null){
            model.addAttribute("movie", movie);
        }
        return "/admin/adminUpdateMovie";
    }
    @PostMapping("/adminUpdateMovie")
    public String adminUpdateMovie(@ModelAttribute MovieDto movieDto){
        Movie movie = movieDto.toEntity();
        movieRepository.save(movie);
        return "redirect:/adminManageMovie";
    }
    @PostMapping("/adminSearchMovie")
    public String adminSearchMovie( @RequestParam(name="name", required = false) String name,
                                    @RequestParam(value="page", required = false, defaultValue = "1")int page,
                                    Model model){
        if (name!=null) {
            List<Movie> searchData = adminService.pagingListByMovieName(page, name);
            if(searchData!=null){
                model.addAttribute("pagingList", searchData);
            }
            PageDto pageDTO = adminService.pagingParamByMovieName(page, name);
            model.addAttribute("paging",pageDTO);
        }
        return "/admin/adminSearchMovie";
    }
    @GetMapping("/adminManageTheater")
    public String adminManageTheater(Model model,  @RequestParam(value="page", required = false,defaultValue = "1")int page){
        System.out.println("page = " + page);
        // 해당 페이지에서 보여줄 글 목록
        List<Theater> pagingList = adminService.pagingListTheater(page);
        System.out.println("pagingList = " + pagingList);
        PageDto pageDTO = adminService.pagingParam(page);
        model.addAttribute("pagingList", pagingList);
        model.addAttribute("paging",pageDTO);
        return "/admin/adminManageTheater";
    }
    @GetMapping("/adminUpdateTheater")
    public String adminUpdateTheaterPage(Model model, @RequestParam(value = "id") Long id){
        Theater theater = theaterRepository.findById(id).orElse(null);
        if(theater!=null){
            model.addAttribute("theater", theater);
        }
        return "/admin/adminUpdateTheater";
    }
    @PostMapping("/adminUpdateTheater")
    public String adminUpdateTheater(@ModelAttribute TheaterDto theaterDto){
        Theater theater = theaterDto.toEntity();
        theaterRepository.save(theater);
        return "redirect:/adminManageTheater";
    }
    @PostMapping("/adminSearchTheater")
    public String adminSearchTheater(@RequestParam(name="city", required = false) String city,
                                     @RequestParam(name="region", required = false) String region,
                                    @RequestParam(value="page", required = false, defaultValue = "1")int page,
                                    Model model){
        if (city!=null) {
            Theater searchData = adminService.pagingListByCity(page, city);
            if(searchData!=null){
                model.addAttribute("pagingList", searchData);
            }
            PageDto pageDTO = adminService.pagingParamByCity(page, city);
            model.addAttribute("paging",pageDTO);
        }
        else{
            List<Theater> searchData = adminService.pagingListByRegion(page, region);
            if(searchData!=null){
                model.addAttribute("pagingList", searchData);
            }
            PageDto pageDTO = adminService.pagingParamByRegion(page, region);
            model.addAttribute("paging",pageDTO);
        }
        return "/admin/adminSearchTheater";
    }
}
