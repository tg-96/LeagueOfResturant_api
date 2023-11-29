package com.leagueofrestaurant.web.review.service;
import com.leagueofrestaurant.web.common.CommonService;
import com.leagueofrestaurant.web.exception.ErrorCode;
import com.leagueofrestaurant.web.exception.LORException;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.review.domain.Review;
//import com.leagueofrestaurant.web.member.dto.MemberDto;
import com.leagueofrestaurant.web.review.dto.ReceiptInfo;
import com.leagueofrestaurant.web.review.dto.ReviewContent;
import com.leagueofrestaurant.web.review.repository.ReviewRepository;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final CommonService commonService;


    //모든 리뷰 조회
    public List<ReviewContent> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();

        return reviews.stream()
                .map(review -> ReviewContent.create(review.getContent(), review.getImg(), review.getSeason()))
                .collect(Collectors.toList());
    }

    //특정 리뷰 조회
    public ReviewContent getReview(Long reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            return ReviewContent.create(review.getContent(), review.getImg(), review.getSeason());
        } else {
            throw new LORException(ErrorCode.NOT_EXIST_REVIEW);
        }
    }

    //특정 회원의 리뷰 조희
    public List<ReviewContent> getReviewsByMemberId(Long memberId) {
        List<Review> reviews = reviewRepository.findAllByMemberId(memberId);

        return reviews.stream()
                .map(review -> ReviewContent.create(review.getContent(), review.getImg(), review.getSeason()))
                .collect(Collectors.toList());
    }

    //특정 가게의 리뷰 조회
    public List<ReviewContent> getReviewsByStoreId(Long storeId) {
        List<Review> reviews = reviewRepository.findAllByStoreId(storeId);

        return reviews.stream()
                .map(review -> ReviewContent.create(review.getContent(), review.getImg(), review.getSeason()))
                .collect(Collectors.toList());
    }

    //영수증 인증
    public ReceiptInfo getReceiptInfo(String receiptImg) {
        /* 영수증 OCR API 사용*/
        return null; // 영수증 정보를 리턴
    }

    //리뷰 작성
    public void createReview(long memberId, long storeId, ReviewContent reviewContent, ReceiptInfo receiptInfo) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new LORException(ErrorCode.NOT_EXIST_MEMBER));

        Store store = storeRepository.findById(storeId).orElse(null);

        // 가게가 존재하지 않는 경우 새로운 가게를 생성
        if (store == null) {
            // 생성 로직, 여기서 가게 생성을 위한 createStore Service 함수를 호출하면 될 것 같은데
        }

        String content = reviewContent.getContent();
        String img = reviewContent.getImg();
        String season = commonService.getSeason();

        // 리뷰 객체 생성
//        Review review = new Review(member, store, content, season, img);

//        // 데이터베이스에 리뷰 저장
//        reviewRepository.save(review);
    }

    //리뷰 삭제
    public void deleteReview(long reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new LORException(ErrorCode.NOT_EXIST_REVIEW);
        }
    }
}
