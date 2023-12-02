package com.leagueofrestaurant.api.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

@Service
public class KakaoService {
    //kakao secret key
    @Value("${kakao.rest-api-key}")
    private String kakaoSecretKey;

    @Value("${kakao.rest-api-url}")
    private String kakaoSearchUrl;

    public String fetchKakaoSearch(String storeName) {
        int size = 30;
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

    public CrawlingStoreDto selectStore(String response, String receiptAddress) throws JsonProcessingException {
        //좌표값 기준으로 개선 예정, 일단은 첫 번째 인덱스를 추출
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        JsonNode storeList = jsonNode.get("documents");
        if(storeList.get(0) != null){
            JsonNode correctStore = storeList.get(0);
            double max_similarity = 0.0;
            for (int i=0; i<storeList.size(); i++) {
                JsonNode store = storeList.get(i);
                String address = store.get("address_name").asText();
                String roadAddress = store.get("road_address_name").asText();
                double similarity_1 = findSimilarity(receiptAddress, address);
                double similarity_2 = findSimilarity(receiptAddress, roadAddress);
                if(similarity_1 > max_similarity || similarity_2 > max_similarity) {
                    max_similarity = Math.max(similarity_1, similarity_2);
                    correctStore = store;
                }
            }
            CrawlingStoreDto crawlingStoreDto = new CrawlingStoreDto(correctStore.get("place_url").asText(), correctStore.get("category_name").asText());
            return crawlingStoreDto;
        }
        else return null;
    }

    public static double findSimilarity(String x, String y) {
        double maxLength = Double.max(x.length(), y.length());
        if (maxLength > 0) {
            return (maxLength - StringUtils.getLevenshteinDistance(x, y)) / maxLength;
        }
        return 1.0;
    }
}
