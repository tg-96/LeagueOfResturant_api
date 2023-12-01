package com.leagueofrestaurant.web.seasonRank.respository;

import com.leagueofrestaurant.web.seasonRank.domain.SeasonRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SeasonRankRepository extends JpaRepository<SeasonRank,Long>,SeasonRankRepositoryCustom, QuerydslPredicateExecutor {

}
