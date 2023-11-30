package com.leagueofrestaurant.web.report.repository;

import com.leagueofrestaurant.web.report.domain.Report;
import com.leagueofrestaurant.web.review.repository.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryCustom, QuerydslPredicateExecutor {
    @Override
    List<Report> findAll();

    @Override
    Optional<Report> findById(Long reportId);
}
