package com.leagueofrestaurant.web.report.dto;

import com.leagueofrestaurant.web.common.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UpdateReportDto {
    private Status status;
}
