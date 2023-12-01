package com.leagueofrestaurant.web.common;

import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class CrawlingService {
    private static final Pattern GET_NUMBER = Pattern.compile("[0-9]+");
    public String crawlStoreImageUrl(String kakaoStorePageUrl) throws IOException {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = GET_NUMBER.matcher(kakaoStorePageUrl);

        while (matcher.find()) {
            sb.append(matcher.group());
        }
        System.out.println(sb.toString());
        String printPageUrl = "https://place.map.kakao.com/placePrint.daum?confirmid=" + sb.toString();
        System.out.println(printPageUrl);

        Document document = Jsoup.connect(printPageUrl).get();
        Elements images = document.select("img");
        // 첫 번째 이미지의 링크 가져오기
        String imageUrl = null;
        if (!images.isEmpty()) {
            Element firstImage = images.first();
            imageUrl = "https:" + firstImage.attr("src");
            System.out.println("첫 번째 이미지 링크: " + imageUrl);
        } else {
            System.out.println("이미지를 찾을 수 없습니다.");
        }
        return imageUrl;
    }

}
