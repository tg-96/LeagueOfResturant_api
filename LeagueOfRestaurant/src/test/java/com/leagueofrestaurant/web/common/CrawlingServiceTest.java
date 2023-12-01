//package com.leagueofrestaurant.web.common;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.*;
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class CrawlingServiceTest {
//    @Autowired
//    CrawlingService crawlingService;
//
//    @Test
//    public void 테스트() throws IOException {
//        String a = crawlingService.crawlStoreImageUrl("https://place.map.kakao.com/23700491");
//        System.out.println("결과: "+ a);
//    }
//
//}