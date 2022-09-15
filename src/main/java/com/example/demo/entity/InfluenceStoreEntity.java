package com.example.demo.entity;

import com.example.demo.dto.InfluenceStoreDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Location")
public class InfluenceStoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lId")
    private Long id;

    @Column(name = "locationName")
    private String name;

    @Column(name = "locationAddress")
    private String address;

    @Column(name = "locationX")
    private Double lat;

    @Column(name = "locationY")
    private Double lng;

    public InfluenceStoreEntity(InfluenceStoreDTO influenceStoreDTO) {
        this.name = influenceStoreDTO.getName();
        this.address = influenceStoreDTO.getAddress();
        this.lat = influenceStoreDTO.getLat();
        this.lng = influenceStoreDTO.getLng();
    }

    public InfluenceStoreEntity() {

    }
}
