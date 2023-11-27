package com.leagueofrestaurant.web.review.repository;

import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom, QuerydslPredicateExecutor {
    @Override
    List<Review> findAll();

    @Override
    Optional<Review> findById(Long reviewId);
}
