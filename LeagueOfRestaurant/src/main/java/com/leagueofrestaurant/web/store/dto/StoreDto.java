package com.leagueofrestaurant.web.store.dto;

import com.leagueofrestaurant.web.store.domain.Address;
import lombok.Getter;

@Getter
public class StoreDto {
    private Long id;
    private String name;
    private Address adress;
    private String img;

    public StoreDto(String name, Address adress, String img,Long id) {
        this.name = name;
        this.adress = adress;
        this.img = img;
        this.id = id;
    }
}
