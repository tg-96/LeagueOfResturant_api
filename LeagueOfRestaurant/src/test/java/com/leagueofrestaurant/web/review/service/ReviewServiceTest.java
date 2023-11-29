package com.leagueofrestaurant.web.review.service;

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
public class ReviewServiceTest {
    @Autowired
    ReviewService reviewService;
    @Test
    public void 날짜테스트(){
        String a = reviewService.getSeasonTest();
        System.out.println(a);
    }

}