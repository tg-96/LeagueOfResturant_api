package com.leagueofrestaurant.api.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public String selectStore(String response) throws JsonProcessingException {
        //좌표값 기준으로 개선 예정, 일단은 첫 번째 인덱스를 추출
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);

        JsonNode firstPlace = jsonNode.get("documents").get(0);

        String placeUrl = firstPlace.get("place_url").asText();
        return  placeUrl;
    }
}
