package com.leagueofrestaurant.web.seasonRank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SeasonRankDto {
    private String storeName;
    private Long storeId;
    private String city;
    private String season;
    private Integer ranking;
    private String img;
}

