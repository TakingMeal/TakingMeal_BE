package com.example.demo.controller;

import com.example.demo.entity.CardApiEntity;
import com.example.demo.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoadApiController {
    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/loadapi")
    public List<CardApiEntity> loadApi(){
        List<CardApiEntity> apiList = cardRepository.findAll();
        return apiList;
    }
}
