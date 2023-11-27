package com.leagueofrestaurant.web.review.repository;

import com.leagueofrestaurant.web.member.domain.Gender;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.domain.MemberType;
import com.leagueofrestaurant.web.review.domain.Review;
import com.leagueofrestaurant.web.store.domain.Address;
import com.leagueofrestaurant.web.store.domain.Store;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReviewRepositoryTest {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired

    @Test
    public void 전체리뷰_조회() {
        Address address = new Address("seoul","3cmd","342");
        Member member1 = new Member("한규정", "010-3022-1161", "msp214314", Gender.MALE, LocalDate.now(), MemberType.USER);
        Store store1 = new Store("가게", address, "dfd");


        Review review1 = new Review(5, "좋아요", "이미지1", member1, store1);
        Review review2 = new Review(4, "괜찮아요", "이미지2", member1, store1);

        // Member와 Store를 먼저 저장
//        memberRepository.saveAndFlush(member1);
//        storeRepository.saveAndFlush(store1);

        reviewRepository.saveAndFlush(review1);
        reviewRepository.saveAndFlush(review2);

        // 전체 리뷰 조회
        List<Review> allReviews = reviewRepository.findAll();

        System.out.println(allReviews);

        // 테스트 검증
    }
}