package com.leagueofrestaurant.web.seasonRank.controller;

import com.leagueofrestaurant.web.seasonRank.dto.SeasonRankDto;
import com.leagueofrestaurant.web.seasonRank.service.SeasonRankService;
import com.leagueofrestaurant.web.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeasonRankController {
    private final SeasonRankService seasonRankService;
    private final StoreService storeService;

    @GetMapping("/seasonRank/{season}/{city}")
    public List<SeasonRankDto> getSeasonRankByCity(@PathVariable("season") String season,
                                                   @PathVariable("city") String city) {
        return seasonRankService.getSeasonRankByCity(season, city);
    }

    @GetMapping("/seasonRank/store/{storeId}")
    public List<SeasonRankDto> getSeasonRankByStoreId(@PathVariable("storeId") Long storeId) {
        return seasonRankService.getSeasonRankByStoreId(storeId);
    }

    @GetMapping("/seasonRank/seasonName")
    public List<String> getSeasonName() {
        return seasonRankService.getSeasonName();
    }

    /**
     * @return 분기 마지막날에 시즌 종료
     */
    @Scheduled(cron = "30 59 23 L * ?")
    public void endSeason() {
        LocalDate today = LocalDate.now();
        if (today.getMonth().equals(Month.FEBRUARY) ||
                today.getMonth().equals(Month.MAY) ||
                today.getMonth().equals(Month.AUGUST) ||
                today.getMonth().equals(Month.NOVEMBER)) {
            //현재 시즌 상위 10개 가게 Season Rank 테이블에 저장
            seasonRankService.saveSeasonRank();
            //스토어의 가게 rating,score 초기화
            storeService.initRank();
        }
    }

}
