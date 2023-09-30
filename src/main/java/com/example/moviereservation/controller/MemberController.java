package com.example.moviereservation.controller;

import com.example.moviereservation.dto.MemberDto;
import com.example.moviereservation.entity.Member;
import com.example.moviereservation.entity.Reserve;
import com.example.moviereservation.repository.MemberRepository;
import com.example.moviereservation.repository.ReserveRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    ReserveRepository reserveRepository;
    @Autowired
    MemberRepository memberRepository;
    @GetMapping("/create")
    public String createMember(){
        return "/member/createmember";
    }
    @PostMapping("/create")
    public String create(MemberDto memberDto){
        Member member = memberDto.toEntity();
        Member saved = memberRepository.save(member);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String loginPage(HttpSession session,HttpServletRequest request){
        String referer = request.getHeader("Referer");
        System.out.println("referer = " + referer);
        session.setAttribute("beforepage", referer);
        return "/member/login";
    }
    @PostMapping("/login")
    public String login(MemberDto memberDto ,HttpSession session, HttpServletRequest request){
        String page = (String) session.getAttribute("beforepage");
        Member member = memberDto.toEntity();
        Member login = memberRepository.findMember(member.getEmail(), member.getPassword());
        if(login != null){
            session.setAttribute("loginuser",login);
            return "redirect:" + page;
        }
        return "/member/loginfail";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request){
        session.removeAttribute("loginuser");
        return "redirect:/movie";
    }
    @GetMapping("/mypage")
    public String mypage(Model model, HttpSession session){
        Member user = (Member) session.getAttribute("loginuser");
        model.addAttribute("loginuser", user);
        return "/member/mypage";
    }
    @GetMapping("myTicket")
    public String myTicket(Model model, HttpSession session){
        Member user = (Member) session.getAttribute("loginuser");
        model.addAttribute("loginuser", user);
        List<Reserve> reserve = reserveRepository.findByMemberId(user.getId());
        model.addAttribute("reserveMovies", reserve);
        return "/member/myTicket";
    }
    @GetMapping("updateInfoPage")
    public String updateInfoPage(Model model, HttpSession session) {
        Member user = (Member) session.getAttribute("loginuser");
        model.addAttribute("loginuser", user);
        return "/member/updateInfo";
    }
    @PostMapping("updateInfo")
    public String updateInfo(@ModelAttribute MemberDto memberDto){
        Member update = memberRepository.findById(memberDto.getId()).orElse(null);
        update.patch(memberDto);
        memberRepository.save(update);
        return "redirect:/mypage";
    }
    @GetMapping("/deleteAccountPage")
    public String deleteAccountPage(HttpSession session,Model model){
        Member user = (Member) session.getAttribute("loginuser");
        model.addAttribute("loginuser", user);
        return "/member/deleteAccount";
    }
    @PostMapping("/deleteAccount")
    public String deleteAccount(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Member user = (Member) session.getAttribute("loginuser");
        Member delete = memberRepository.findMember(email, password);
        if (user.getId() == delete.getId()) {
            memberRepository.delete(delete);
            session.removeAttribute("loginuser");
            return "redirect:/movie";
        } else {
            return "redirect:/mypage";
        }
    }
}
