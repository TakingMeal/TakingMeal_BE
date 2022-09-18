package com.example.demo.repository;

import com.example.demo.entity.InfluenceStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluenceStoreRepository extends JpaRepository<InfluenceStoreEntity, Long> {
}
