package com.leagueofrestaurant.web.store.dto;

import com.leagueofrestaurant.web.common.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreSearchCondition {
    private String name;
    private String city;
    public StoreSearchCondition(String name,String city) {
        this.name = name;
        this.city = city;
    }
}
