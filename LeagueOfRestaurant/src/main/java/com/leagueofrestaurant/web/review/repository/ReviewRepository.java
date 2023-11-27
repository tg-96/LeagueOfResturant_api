package com.leagueofrestaurant.web.review.repository;

import com.leagueofrestaurant.web.member.repository.MemberRepositoryCustom;
import com.leagueofrestaurant.web.review.domain.Review;
import com.leagueofrestaurant.web.member.domain.Member;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom, QuerydslPredicateExecutor {
    @Override
    List<Review> findAll();

    @Override
    Optional<Review> findById(Long reviewId);

}
