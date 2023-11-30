package com.leagueofrestaurant.web.report.dto;


import com.leagueofrestaurant.web.common.Status;
import com.leagueofrestaurant.web.report.domain.Type;
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
    private Status status;
    private Long member_id;
    private Long review_id;
}
