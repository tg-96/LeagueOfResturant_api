package com.leagueofrestaurant.web.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

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
        }
        else { // 12,1,2월의 경우
            if(month.getValue() == 1 || month.getValue() == 2){
                year--;
            }
            season = "Winter";
        }
        return year + "-" + season;
    }

    public String getCityString(String address) {
        List<String> tokens = Arrays.asList(address.split(" "));
        String extractedToken = null;

        // '시' 또는 '군'으로 끝나는 토큰 추출
        for (String token : tokens) {
            if (token.equals("서울") || token.equals("서울특별시")){
                return "서울";
            }
            else if (token.equals("인천") || token.equals("인천광역시")){
                return "인천";
            }
            else if (token.endsWith("시") || token.endsWith("군")) {
                extractedToken = token;
                break;
            }
        }

        if (extractedToken != null) {
            extractedToken = extractedToken.substring(0, extractedToken.length() - 1);
            return extractedToken;
        } else {
            // default
            return "Unknown";
        }
    }

    /**
     * 리팩토링 : Set으로 바꾸면 시간 복잡도 면에서 더 좋으려나...?
     */
    public boolean isBoundary(String city) {
        String[] cities = {
                "서울", "수원", "성남", "용인", "안양", "안산",
                "과천", "광명", "광주", "군포", "부천",
                "시흥", "김포", "안성", "오산", "의왕",
                "이천", "평택", "하남", "화성", "여주", "고양",
                "구리", "의정부", "양주", "남양주", "파주", "포천", "인천"
        };
        for (String s : cities) {
            if (s.equals(city)) {
                return true;
            }
        }
        return false;
    }
}
