package com.leagueofrestaurant.web.store.dto;

import lombok.Getter;

@Getter
public class RequestStoreDto {
    private String name;
    private String address;
    private String city;
    private String img;

    public RequestStoreDto(String name, String address, String city, String img) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.img = img;
    }
}
