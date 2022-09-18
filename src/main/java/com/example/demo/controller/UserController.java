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

    @GetMapping("/signIn")
    public String signIn(@ModelAttribute User user, Model model){


        return "/signIn";
    }

    @PostMapping("/signInPro")
    public String signInPro(@ModelAttribute User user, Model model, HttpServletRequest request){

       userRepository.save(user);

        return "/index";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute User user, Model model){


        return "/login";
    }

    @PostMapping("/loginPro")
    public String loginPro(@ModelAttribute User user, Model model, HttpServletRequest request, HttpServletResponse response){

        if(user.getUserId() == null){
            return "/signIn";
        }

        if(user.getUserId().equals((userRepository.findUserByUserId(user.getUserId()).getUserId()))){
            System.out.println("아이디 일치");

            if(user.getUserPw().equals(userRepository.findUserByUserId(user.getUserId()).getUserPw())){
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
    @GetMapping("/getCookie1")
    public String getCookie1(@CookieValue String userId) {
        System.out.println(userId);
        return "/index";
    }
}
