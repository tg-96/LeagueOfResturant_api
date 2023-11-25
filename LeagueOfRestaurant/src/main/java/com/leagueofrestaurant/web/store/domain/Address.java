package com.leagueofrestaurant.web.store.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String district;
    private String street;
}
