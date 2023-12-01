package com.leagueofrestaurant.api.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KakaoService {
    //kakao secret key
    @Value("${kakao.rest-api-key}")
    private String kakaoSecretKey;

    @Value("${kakao.rest-api-url}")
    private String kakaoSearchUrl;

    public String fetchKakaoSearch(String storeName) {
        WebClient webClient = WebClient.builder()
                .baseUrl(kakaoSearchUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization", "KakaoAK " + kakaoSecretKey)
                .build();

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/keyword.json")
                        .queryParam("query", storeName)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
