package com.leagueofrestaurant.web.store.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreSearchCondition {
    private String name;
    private String city;
    private String address;

    public StoreSearchCondition(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }
}
