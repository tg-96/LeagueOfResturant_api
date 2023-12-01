package com.leagueofrestaurant.web.seasonRank.respository;

import com.leagueofrestaurant.web.seasonRank.domain.SeasonRank;

import java.util.List;

public interface SeasonRankRepositoryCustom {
    //해당 스토어의 모든 시즌랭크 조회
    List<SeasonRank> findSeasonRankByStoreId(Long storeId);
    //city의 특정 시즌 랭크 조회
    List<SeasonRank> findSeasonRankByCity(String Season,String city);
    List<String> getSeasonName();
}
