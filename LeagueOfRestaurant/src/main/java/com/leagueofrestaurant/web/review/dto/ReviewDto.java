package com.leagueofrestaurant.web.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private String content;
    private Integer ratingPoint;
    private String storeName;
    private String address;

}
