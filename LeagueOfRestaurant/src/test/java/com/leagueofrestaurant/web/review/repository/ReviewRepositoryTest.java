package com.leagueofrestaurant.web.review.repository;

<<<<<<< HEAD
import com.leagueofrestaurant.web.member.domain.Gender;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.domain.MemberType;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import com.leagueofrestaurant.web.review.repository.ReviewRepositoryCustom;
import com.leagueofrestaurant.web.review.domain.Review;
import com.leagueofrestaurant.web.store.domain.Store;
=======
import com.leagueofrestaurant.web.review.service.ReviewService;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
>>>>>>> feat/review-svc
import com.leagueofrestaurant.web.store.repository.StoreRepository;
import org.apache.tomcat.jni.Address;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional


class ReviewRepositoryTest {
    @Autowired
    ReviewRepository reviewRepository;
    StoreRepository storeRepository;
    @Test
<<<<<<< HEAD
    public void 전체리뷰_조회() {
        Member member1 = new Member("한규정", "010-3022-1161", "msp214314", Gender.MALE, LocalDate.now(), MemberType.USER);
        Member member2 = new Member("한투정", "010-3022-1161", "msp214314", Gender.MALE, LocalDate.now(), MemberType.USER);
        Store store1 = new Store("가게","asd","asd","dfd");
        Store store2 = new Store("가게2", "Asdas","asd", "dfd");

        // Member와 Store를 먼저 저장
        memberRepository.saveAndFlush(member1);
        memberRepository.saveAndFlush(member2);
        storeRepository.saveAndFlush(store1);
        storeRepository.saveAndFlush(store2);

        Review review1 = new Review(5, "좋아요", "2023-Winter", "이미지1", member1, store1);
        Review review2 = new Review(4, "괜찮아요", "2023-Winter", "이미지2",  member1, store2);
        Review review3 = new Review(3, "괜찮아2", "2023-Winter", "이미지3", member2, store1);


        reviewRepository.saveAndFlush(review1);
        reviewRepository.saveAndFlush(review2);
        reviewRepository.saveAndFlush(review3);

        // 전체 리뷰 조회
        List<Review> allReviews = reviewRepository.findAll();
        List<Review> memberIdReviews = reviewRepository.findAllByStoreId(store1.getId());

        for (Review review : allReviews) {
            System.out.println("Review ID: " + review.getId());
            System.out.println("Rating: " + review.getRatingPoint());
            System.out.println("Comment: " + review.getContent());
            System.out.println("Image: " + review.getImg());
            System.out.println("Member: " + review.getMember().getId());
            System.out.println("Store: " + review.getStore().getId());
            System.out.println("------------------------------------");
        }

        for (Review review : memberIdReviews) {
            System.out.println("Review ID: " + review.getId());
            System.out.println("Rating: " + review.getRatingPoint());
            System.out.println("Comment: " + review.getContent());
            System.out.println("Image: " + review.getImg());
            System.out.println("Member: " + review.getMember().getId());
            System.out.println("Store: " + review.getStore().getId());
            System.out.println("------------------------------------");
        }


        // 테스트 검증
=======
    public void 테스트() {
//        StoreSearchCondition storeSearchCondition = new StoreSearchCondition('탐앤탐스 아주대점', '수원');
//        storeRepository.findStoreListByCondition()
>>>>>>> feat/review-svc
    }

    @Test
    @DisplayName("가게의 리뷰수 카운트")
    public void countReview(){
        Long count = reviewRepository.countByStoreId(2L);
        assertThat(count).isEqualTo(1);
    }
}