package com.leagueofrestaurant.web.store.dto;

import lombok.Getter;

@Getter
public class ResponseStoreDto {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String img;

    public ResponseStoreDto(Long id, String name, String address, String city, String img) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.img = img;
    }
}
