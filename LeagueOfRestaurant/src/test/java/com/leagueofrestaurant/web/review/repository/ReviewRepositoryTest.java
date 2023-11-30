package com.leagueofrestaurant.web.review.repository;

import com.leagueofrestaurant.web.review.service.ReviewService;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import com.leagueofrestaurant.web.store.repository.StoreRepository;
import org.junit.Test;
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
    public void 테스트() {
//        StoreSearchCondition storeSearchCondition = new StoreSearchCondition('탐앤탐스 아주대점', '수원');
//        storeRepository.findStoreListByCondition()
    }
}