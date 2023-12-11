package com.leagueofrestaurant.web.common;

import com.leagueofrestaurant.web.seasonRank.domain.SeasonRank;
import com.leagueofrestaurant.web.seasonRank.respository.SeasonRankRepository;
import com.leagueofrestaurant.web.seasonRank.service.SeasonRankService;
import com.leagueofrestaurant.web.store.service.StoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class CommonServiceTest {
    @Autowired
    CommonService commonService;
    @Autowired
    StoreService storeService;
    @Autowired
    SeasonRankService seasonRankService;
    @Autowired
    SeasonRankRepository seasonRankRepository;
    @Test
    @Rollback(value = false)
    public void 테스트() {
//      seasonRankRepository.deleteAll();
        seasonRankService.saveSeasonRank();
    }


}