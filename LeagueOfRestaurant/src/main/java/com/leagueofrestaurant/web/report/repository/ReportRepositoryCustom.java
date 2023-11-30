package com.leagueofrestaurant.web.report.repository;

import com.leagueofrestaurant.web.common.Status;
import com.leagueofrestaurant.web.report.domain.Report;
import com.leagueofrestaurant.web.review.domain.Review;

import java.util.List;


public interface  ReportRepositoryCustom {
    List<Report> findAllByMemberId(Long member_Id);

    List<Report> findAllByStatus(Status status);
}