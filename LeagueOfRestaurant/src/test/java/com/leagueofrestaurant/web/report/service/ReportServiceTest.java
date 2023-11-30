package com.leagueofrestaurant.web.report.service;

import com.leagueofrestaurant.web.common.Status;
import com.leagueofrestaurant.web.report.domain.Report;
import com.leagueofrestaurant.web.report.domain.Type;
import com.leagueofrestaurant.web.report.dto.ReportDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReportServiceTest {
    @Autowired
    ReportService reportService;

    @Test
    public void 테스트(){
//        ReportDto reportDto = new ReportDto(Type.OBSCENE_LANGUAGE, "이상한 소리함", Status.PROCESSING, 2L, 5L);
//        reportService.createReport(reportDto);
//        System.out.println(reportService.getAllReports().get(0));
    }

}