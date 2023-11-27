package com.leagueofrestaurant.web.review.dto;
import lombok.Getter;

@Getter
public class ReviewContent {
    private String content;
    private String img;
    public static ReviewContent create(String content, String img){
        ReviewContent reviewContent = new ReviewContent();
        reviewContent.content = content;
        reviewContent.img = img;
        return reviewContent;
    }
}
