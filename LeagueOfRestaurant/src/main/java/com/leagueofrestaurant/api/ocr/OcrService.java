package com.leagueofrestaurant.api.ocr;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;


@Service
public class OcrService {
    //ocr secret key
    @Value("${naver.cloud.secret-key}")
    private String naverSecretKey;
    //ocr api gateway
    @Value("${naver.cloud.gateway-url}")
    private String naverGatewayUrl;
    @Autowired
    private ObjectMapper objectMapper;

    //영수증 인식 및 json 반환
    public String getReceiptJSON(String imagePath) {
        org.springframework.web.reactive.function.client.WebClient webClient = org.springframework.web.reactive.function.client.WebClient.builder()
                .baseUrl(naverGatewayUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
                .defaultHeader("X-OCR-SECRET", naverSecretKey)
                .build();

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("message", "{\"images\":[{\"format\":\"png\",\"name\":\"demo\"}],\"requestId\":\"guide-demo\",\"version\":\"V2\",\"timestamp\":1584062336793}");
        formData.add("file", new FileSystemResource(imagePath));

        return webClient.post()
                .uri("/")
                .body(BodyInserters.fromMultipartData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // json 데이터 가공
    public List<String> getReceiptInfo(JsonNode json) {
        String storeName = json.at("/images/0/receipt/result/storeInfo/name/text").asText();
        String storeSubName = json.at("/images/0/receipt/result/storeInfo/subName/text").asText();
        String storeAddress = json.at("/images/0/receipt/result/storeInfo/addresses/0/text").asText();
        String message = json.at("/images/0/message").asText();

        if (storeSubName.matches("[\\[\\(].*[]\\)]")) {
            storeSubName = storeSubName.substring(1, storeSubName.length() - 1);
        }
        List<String> result = new ArrayList<>();
        result.add(message);
        result.add(storeName + " " + storeSubName);
        result.add(storeAddress);
        return result;
    }

}