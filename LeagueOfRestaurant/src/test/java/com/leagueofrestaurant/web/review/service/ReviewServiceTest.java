package com.leagueofrestaurant.web.review.service;

import com.leagueofrestaurant.web.review.dto.ReceiptInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReviewServiceTest {
    @Autowired
    ReviewService reviewService;

    @Test
    public void 테스트() throws IOException {
//        ReceiptInfo receiptInfo = reviewService.getReceiptInfo("");
//        System.out.println(receiptInfo.getStoreName());
//        System.out.println(receiptInfo.getAddress());
    }
}