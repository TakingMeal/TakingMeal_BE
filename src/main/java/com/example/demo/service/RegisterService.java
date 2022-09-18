package com.example.demo.service;

import com.example.demo.dto.MealDto;
import com.example.demo.entity.Meal;
import com.example.demo.entity.User;
import com.example.demo.repository.MealRepository;
import com.example.demo.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;

    private final MealRepository mealRepository;

    //식단 추가
    public String foodRegister(MealDto mealDto){

        String userId = mealDto.getUserId();
        User user = userRepository.findUserByUserId(userId);
        Meal meal = Meal.builder()
                .user(user)
                .mealName(mealDto.getMealName())
                .mealAmount(mealDto.getMealAmount())
                .mealCal(mealDto.getMealCal())
                .mealCarbon(mealDto.getMealCarbon())
                .mealProtein(mealDto.getMealProtein())
                .mealFat(mealDto.getMealFat())
                .build();


        mealRepository.save(meal);

        return "success";
    }




}