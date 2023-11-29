package com.leagueofrestaurant.web.review.controller;

import com.leagueofrestaurant.web.review.dto.ReviewContent;
import com.leagueofrestaurant.web.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //모든 리뷰 조회
    @GetMapping("/")
    public List<ReviewContent> getAllReviews() {
        return reviewService.getAllReviews();
    }

    //특정 리뷰 조회
    @GetMapping("/{reviewId}")
    public ReviewContent getReivew(@PathVariable Long reviewId) {
        return reviewService.getReview(reviewId);
    }

    //특정 사용자의 리뷰 조회
    @GetMapping("/member/{memberId}")
    public List<ReviewContent> getReviewsByMemberId(@PathVariable Long memberId) {
        return reviewService.getReviewsByMemberId(memberId);
    }

    //특정 가게의 리뷰 조회
    @GetMapping("/store/{storeId}")
    public List<ReviewContent> getReviewsByStoreId(@PathVariable Long storeId) {
        return reviewService.getReviewsByStoreId(storeId);
    }

    //영수증 인증

    //리뷰 작성

    //리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }
}
