package com.leagueofrestaurant.web.report.repository;

import com.leagueofrestaurant.web.review.domain.Review;
import com.leagueofrestaurant.web.review.repository.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom, QuerydslPredicateExecutor {
    @Override
    List<Review> findAll();

    @Override
    Optional<Review> findById(Long reviewId);
}
