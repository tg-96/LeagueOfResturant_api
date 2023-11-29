package com.leagueofrestaurant.web.review.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReviewContent {
    private String content;
    private String img;
    private String season;
}
