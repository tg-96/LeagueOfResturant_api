package com.leagueofrestaurant.web.wish.repository;

import com.leagueofrestaurant.web.wish.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface WishRepository extends JpaRepository<Wish, Long>, WishRepositoryCustom, QuerydslPredicateExecutor {

}
