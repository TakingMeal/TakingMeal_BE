package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InfluenceStoreDTO {

    private String name;
    private String address;
    private Double lat;
    private Double lng;

    @Builder
    public InfluenceStoreDTO(String name, String address, Double lat, Double lng) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }
}
