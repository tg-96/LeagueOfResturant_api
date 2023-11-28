package com.leagueofrestaurant.web.review.controller;

import com.leagueofrestaurant.web.review.dto.ReviewContent;
import com.leagueofrestaurant.web.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/")
    public List<ReviewContent> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/member/{memberId}")
    public List<ReviewContent> getReviewsByMemberId(@PathVariable Long memberId) {
        return reviewService.getReviewsByMemberId(memberId);
    }

    @GetMapping("/store/{storeId}")
    public List<ReviewContent> getReviewsByStoreId(@PathVariable Long storeId) {
        return reviewService.getReviewsByStoreId(storeId);
    }

}
