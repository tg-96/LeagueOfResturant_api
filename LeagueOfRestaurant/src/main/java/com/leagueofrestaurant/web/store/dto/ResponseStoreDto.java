package com.leagueofrestaurant.web.store.dto;

import lombok.Getter;

@Getter
public class ResponseStoreDto {
    private Long storeId;
    private String name;
    private String address;
    private String city;
    private String img;
    private float rating;
    private float score;

    public ResponseStoreDto(Long id, String name, String address, String city, String img, float rating,float score) {
        this.storeId = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.img = img;
        this.rating = rating;
        this.score = score;
    }
}
