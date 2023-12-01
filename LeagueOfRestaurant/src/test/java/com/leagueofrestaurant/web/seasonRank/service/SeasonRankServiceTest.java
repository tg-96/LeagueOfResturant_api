//package com.leagueofrestaurant.web.seasonRank.service;
//
//import com.leagueofrestaurant.web.seasonRank.respository.SeasonRankRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//class SeasonRankServiceTest {
//    @Autowired
//    private SeasonRankService seasonRankService;
//    @Autowired
//    private SeasonRankRepository seasonRankRepository;
//
//    @Test
//    @DisplayName("랭킹 테이블에 저장이 되는지 확인")
//    @Rollback(value = false)
//    public void saveSeasonRank() {
//        seasonRankRepository.deleteAll();
//    }
//
//}