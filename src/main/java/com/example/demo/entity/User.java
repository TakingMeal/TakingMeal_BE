package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@Table(name = "User")
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
    private String userAge;

    @Column(name = "userGender")
    private String userGender;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Meal> meals = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return uId != null && Objects.equals(uId, user.uId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
