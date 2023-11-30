package com.leagueofrestaurant.web.report.dto;

import com.leagueofrestaurant.web.common.Status;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.report.domain.Type;
import com.leagueofrestaurant.web.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReportDto {
    private Type type;
    private String content;
    private Long memberId;
    private Long reviewId;
}

