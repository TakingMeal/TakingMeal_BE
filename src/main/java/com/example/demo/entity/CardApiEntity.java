package com.example.demo.entity;

import com.example.demo.dto.CardApiDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Location")
public class CardApiEntity {
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

    public CardApiEntity(CardApiDTO cardApiDTO) {
        this.name = cardApiDTO.getName();
        this.address = cardApiDTO.getAddress();
        this.lat = cardApiDTO.getLat();
        this.lng = cardApiDTO.getLng();
    }

    public CardApiEntity() {
    }
}
