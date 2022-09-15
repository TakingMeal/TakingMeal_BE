package com.example.demo.DTO;

import com.example.demo.entity.CardApiEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CardApiDTO {

    private String name;
    private String address;
    private Double lat;
    private Double lng;

    @Builder
    public CardApiDTO(String name, String address, Double lat, Double lng) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }
}
