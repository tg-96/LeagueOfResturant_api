package com.leagueofrestaurant.web.review.dto;
import lombok.Getter;

@Getter
public class ReviewContent {
    private String content;
    private String img;
    private String season;
    public static ReviewContent create(String content, String img, String season){
        ReviewContent reviewContent = new ReviewContent();
        reviewContent.content = content;
        reviewContent.img = img;
        reviewContent.season = season;
        return reviewContent;
    }
}
