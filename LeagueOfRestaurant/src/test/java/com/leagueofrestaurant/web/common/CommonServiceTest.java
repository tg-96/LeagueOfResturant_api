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
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//
//public class CommonServiceTest {
//    @Autowired
//    CommonService commonService;
//
//    @Test
//    public void 테스트() {
//        String a = commonService.getCityString("충청북도 청주시 무슨동 341-23");
//        System.out.println(a);
//        System.out.println(commonService.isBoundary(a));
//    }
//
//}