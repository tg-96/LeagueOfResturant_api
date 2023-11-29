package com.leagueofrestaurant.web.store.dto;

import lombok.Getter;

@Getter
public class RequestStoreDto {
    private String name;
    private String adress;
    private String city;
    private String img;

    public RequestStoreDto(String name, String adress, String city, String img) {
        this.name = name;
        this.adress = adress;
        this.city = city;
        this.img = img;
    }
}
