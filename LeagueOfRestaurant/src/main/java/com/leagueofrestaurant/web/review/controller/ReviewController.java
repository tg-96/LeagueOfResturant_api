package com.leagueofrestaurant.web.review.controller;

import com.leagueofrestaurant.web.common.ImageService;
import com.leagueofrestaurant.web.common.SessionKey;
import com.leagueofrestaurant.web.review.dto.ReceiptInfo;
import com.leagueofrestaurant.web.review.dto.ReviewContent;
import com.leagueofrestaurant.web.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.leagueofrestaurant.web.common.SessionKey.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReviewController {
    private final ReviewService reviewService;
    private final ImageService imageService;

    //모든 리뷰 조회
    @GetMapping("/")
    public List<ReviewContent> getAllReviews() {
        return reviewService.getAllReviews();
    }

    //리뷰 작성
    @PostMapping("/")
    public ResponseEntity<String> createReview( HttpSession session,
                                               @RequestParam("content") String content,
                                               @RequestParam("ratingPoint") Integer ratingPoint,
                                               @RequestParam(value = "image", required = false) MultipartFile image,
                                               @RequestParam("storeName") String storeName,
                                               @RequestParam("address") String address) throws IOException {
        String imageFilePath = null;

        try {
            if (image != null && !image.isEmpty()) {
                imageFilePath = imageService.saveImage(image);
                System.out.println("image saved.");
            }
            ReviewContent reviewContent = new ReviewContent(content, ratingPoint, imageFilePath, null,null);
            ReceiptInfo receiptInfo = new ReceiptInfo(storeName, address);

            reviewService.createReview((Long)session.getAttribute(LOGIN_SESSION_KEY), reviewContent, receiptInfo);
            return ResponseEntity.ok("Review created successfully");
        } catch (Exception e) {
            // 리뷰 생성 중 오류가 발생하면 이미지를 다시 삭제
            if (imageFilePath != null) {
                imageService.deleteImage(imageFilePath);
            }
            throw e;
        }
    }

    //특정 리뷰 조회
    @GetMapping("/{reviewId}")
    public ReviewContent getReivew(@PathVariable Long reviewId) {
        return reviewService.getReview(reviewId);
    }

    //특정 사용자의 리뷰 조회
    @GetMapping("/member")
    public List<ReviewContent> getReviewsByMember(HttpSession session) {
        return reviewService.getReviewsByMemberId((Long)session.getAttribute(LOGIN_SESSION_KEY));
    }

    //특정 가게의 리뷰 조회
    @GetMapping("/store/{storeId}")
    public List<ReviewContent> getReviewsByStoreId(
            @PathVariable Long storeId, String season,
            Pageable pageable) {
        return reviewService.getReviewsByStoreId(season, storeId, pageable);
    }

    //영수증 인증
    @PostMapping("/receipt")
    public ReceiptInfo getReceiptInfo(@RequestParam("image") MultipartFile image) throws IOException {
        return reviewService.getReceiptInfo(image);
    }

    //리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted successfully");
    }
}
