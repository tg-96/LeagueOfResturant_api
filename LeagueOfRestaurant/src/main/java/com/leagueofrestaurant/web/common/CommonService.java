package com.leagueofrestaurant.web.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;

@Service
@RequiredArgsConstructor
public class CommonService {
    //시즌 계산 함수
    public String getSeason() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        Month month = currentDate.getMonth();

        String season;
        if (month.getValue() >= 3 && month.getValue() <= 5) {
            season = "Spring";
        } else if (month.getValue() >= 6 && month.getValue() <= 8) {
            season = "Summer";
        } else if (month.getValue() >= 9 && month.getValue() <= 11) {
            season = "Fall";
        } else { // 1,2월의 경우
            year--;
            season = "Winter";
        }
        return year + "-" + season;
    }
}
