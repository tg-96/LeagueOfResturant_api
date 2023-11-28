package com.leagueofrestaurant.web.review.service;

import com.leagueofrestaurant.web.review.domain.Review;
//import com.leagueofrestaurant.web.member.dto.MemberDto;
import com.leagueofrestaurant.web.review.dto.ReviewContent;
import com.leagueofrestaurant.web.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    //모든 리뷰 조회
    public List<ReviewContent> getAllReviews() {
        List <Review> reviews = reviewRepository.findAll();

        return reviews.stream()
                .map(review -> ReviewContent.create(review.getContent(), review.getImg()))
                .collect(Collectors.toList());
    }

    //특정 회원의 리뷰 조희
    public List<ReviewContent> getReviewsByMemberId(Long memberId) {
        List<Review> reviews = reviewRepository.findAllByMemberId(memberId);

        return reviews.stream()
                .map(review -> ReviewContent.create(review.getContent(), review.getImg()))
                .collect(Collectors.toList());
    }

    //특정 가게의 리뷰 조회
    public List<ReviewContent> getReviewsByStoreId(Long storeId) {
        List<Review> reviews = reviewRepository.findAllByStoreId(storeId);

        return reviews.stream()
                .map(review -> ReviewContent.create(review.getContent(), review.getImg()))
                .collect(Collectors.toList());
    }
}
