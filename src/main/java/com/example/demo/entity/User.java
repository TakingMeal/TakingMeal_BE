package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uId")
    private Long uId;

    @Column(name = "userId")
    private String userId;

    @Column(name = "userPw")
    private String userPw;

    @Column(name = "userAge")
    private int userAge;

    @Column(name = "userGender")
    private String userGender;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)     //일대다 매핑
    private List<Meal> meals = new ArrayList<>();


}
