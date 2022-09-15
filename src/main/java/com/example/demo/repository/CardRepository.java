package com.example.demo.repository;

import com.example.demo.entity.CardApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardApiEntity, Long> {
}
