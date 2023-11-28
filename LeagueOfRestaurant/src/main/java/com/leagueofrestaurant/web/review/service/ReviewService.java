package com.leagueofrestaurant.web.review.service;

import com.leagueofrestaurant.web.exception.ErrorCode;
import com.leagueofrestaurant.web.exception.LORException;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.review.domain.Review;
//import com.leagueofrestaurant.web.member.dto.MemberDto;
import com.leagueofrestaurant.web.review.dto.ReviewContent;
import com.leagueofrestaurant.web.review.repository.ReviewRepository;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    //모든 리뷰 조회
    public List<ReviewContent> getAllReviews() {
        List <Review> reviews = reviewRepository.findAll();

        return reviews.stream()
                .map(review -> ReviewContent.create(review.getContent(), review.getImg()))
                .collect(Collectors.toList());
    }

    //특정 리뷰 조회
    public ReviewContent getReview(Long reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            return ReviewContent.create(review.getContent(), review.getImg());
        } else {
            throw new LORException(ErrorCode.NOT_EXIST_REVIEW);
        }
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

    //영수증 인증

    //리뷰 작성
    public void createReview(long memberId, long storeId, ReviewContent reviewContent){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new LORException(ErrorCode.NOT_EXIST_MEMBER));

        Store store = storeRepository.findById(storeId).orElse(null);

        // 가게가 존재하지 않는 경우 새로운 가게를 생성
        if (store == null) {
            // 생성 로직, 여기서 가게 생성을 위한 createStore Service 함수를 호출하면 될 것 같은데
        }

        String content = reviewContent.getContent();
        String img = reviewContent.getImg();

        // 리뷰 객체 생성
//        Review review = new Review(member, store, content, img);

//        // 데이터베이스에 리뷰 저장
//        reviewRepository.save(review);
    }

    //리뷰 삭제
    public void deleteReview(long reviewId){
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new LORException(ErrorCode.NOT_EXIST_REVIEW);
        }
    }
}
