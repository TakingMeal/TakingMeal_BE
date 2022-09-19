package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.MealRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;


    //회원가입 페이지 랜더링
    @GetMapping("/signIn")
    public String signIn(@ModelAttribute User user, Model model){


        return "/signIn";
    }


    //회원가입 form method = post
    @PostMapping("/signInPro")
    public String signInPro(@ModelAttribute User user, Model model, HttpServletRequest request){

       userRepository.save(user);

        return "/index";
    }


    //로그인 페이지 랜더링
    @GetMapping("/login")
    public String login(@ModelAttribute User user, Model model){


        return "/login";
    }


    //로그인 form method = post
    @PostMapping("/loginPro")
    public String loginPro(@ModelAttribute User user, Model model, HttpServletRequest request, HttpServletResponse response){

        if(user.getUserId() == null){
            return "/signIn";
        }

        if(user.getUserId().equals((userRepository.findByUserId(user.getUserId()).getUserId()))){
            System.out.println("아이디 일치");

            if(user.getUserPw().equals(userRepository.findByUserId(user.getUserId()).getUserPw())){
                System.out.println("로그인 성공");
                Cookie cookie = new Cookie("userId", String.valueOf(user.getUserId()));
                response.addCookie(cookie);

            }
            else
                System.out.println("로그인 실패");
        }
        else{
            System.out.println("아이디 불일치");
        }

        return "redirect:/";
    }

    //쿠키에서 유저정보 조회 api
    @GetMapping("/getCookie1")
    public String getCookie1(@CookieValue String userId) {
        System.out.println(userId);
        return "/index";
    }
}
