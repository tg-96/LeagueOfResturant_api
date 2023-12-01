package com.leagueofrestaurant.web.seasonRank.respository;

import com.leagueofrestaurant.web.seasonRank.domain.QSeasonRank;
import com.leagueofrestaurant.web.seasonRank.domain.SeasonRank;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.leagueofrestaurant.web.seasonRank.domain.QSeasonRank.*;

public class SeasonRankRepositoryCustomImpl implements SeasonRankRepositoryCustom {
    private final JPAQueryFactory query;
    public SeasonRankRepositoryCustomImpl(EntityManager em){
        this.query = new JPAQueryFactory(em);
    }

    /**
     *
     * @param storeId
     * @return 가게의 역대 시즌 랭킹 현황
     */
    @Override
    public List<SeasonRank> findSeasonRankByStoreId(Long storeId) {
        return query.selectFrom(seasonRank)
                .where(seasonRank.storeId.eq(storeId))
                .fetch();
    }

    /**
     *
     * @param season
     * @param city
     * @return 도시별 해당 시즌 랭킹 현황
     */
    @Override
    public List<SeasonRank> findSeasonRankByCity(String season, String city) {
        return query.selectFrom(seasonRank)
                .where(seasonRank.season.eq(season),
                        seasonRank.city.eq(city))
                .fetch();
    }


}
