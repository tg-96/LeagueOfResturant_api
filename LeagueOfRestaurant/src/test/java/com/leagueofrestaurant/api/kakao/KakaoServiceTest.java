//package com.leagueofrestaurant.api.kakao;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//
//public class KakaoServiceTest {
//    @Autowired
//    KakaoService kakaoService;
//
//    @Test
//    public void 테스트() throws JsonProcessingException {
//        String a = kakaoService.fetchKakaoSearch("뜔ㅇㄸ레례ㄸㄹ");
//        CrawlingStoreDto b = kakaoService.selectStore(a, "수원시 팔달구");
//
//        if(b!=null){
//            System.out.println("url: "+b.getStoreUrl());
//            System.out.println("category: "+b.getCategory());
//        } else System.out.println("null");
//
//    }
//
//}