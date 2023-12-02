package com.leagueofrestaurant.web.seasonRank.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeasonRank {
    @Id @GeneratedValue
    @Column(name = "seasonRank_id")
    private Long id;
    private String storeName;
    private Long storeId;
    private String city;
    private String season;
    private Integer ranking;
    private String img;

    public SeasonRank(String storeName, Long storeId, String city, String season, Integer rank,String img) {
        this.storeName = storeName;
        this.storeId = storeId;
        this.city = city;
        this.season = season;
        this.ranking = rank;
        this.img = img;
    }
}
