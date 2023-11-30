package com.leagueofrestaurant.api.ocr;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
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

    //영수증 인식 API 호출 함수
    public String getReceiptJSON(MultipartFile image) throws IOException {
        org.springframework.web.reactive.function.client.WebClient webClient = org.springframework.web.reactive.function.client.WebClient.builder()
                .baseUrl(naverGatewayUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
                .defaultHeader("X-OCR-SECRET", naverSecretKey)
                .build();

        // 이미지 형식 추출
        String imageFormat = getImageFormat(image);
        // 타임스탬프 생성
        long timestamp = System.currentTimeMillis();

        String message = String.format("{\"images\":[{\"format\":\"%s\",\"name\":\"receiptImg\"}],\"requestId\":\"se9\",\"version\":\"V2\",\"timestamp\":%d}", imageFormat, timestamp);

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("message", message);
        formData.add("file", new ByteArrayResource(image.getBytes()) {
            @Override
            public String getFilename() {
                return image.getOriginalFilename();
            }
        });

        return webClient.post()
                .uri("/")
                .body(BodyInserters.fromMultipartData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // 이미지 형식 추출
    private String getImageFormat(MultipartFile image) {
        String originalFilename = image.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        }
        return "png"; // 기본값 설정
    }


    // 영수증 정보 json 데이터 가공 함수
    public List<String> getReceiptInfo(JsonNode json) {
        String storeName = json.at("/images/0/receipt/result/storeInfo/name/text").asText();
        String storeSubName = json.at("/images/0/receipt/result/storeInfo/subName/text").asText();
        String storeAddress = json.at("/images/0/receipt/result/storeInfo/addresses/0/text").asText();
        String message = json.at("/images/0/message").asText();
        storeName = storeName.trim(); //공백 제거
        storeSubName = storeSubName.trim();

        if (storeSubName.matches("[\\[\\(].*[]\\)]")) {  //지점명에 괄호가 있다면 제거
            storeSubName = storeSubName.substring(1, storeSubName.length() - 1);
        }

        List<String> result = new ArrayList<>();
        result.add(message);
        if(storeSubName.isEmpty()){
            result.add(storeName);
        }
        else{
            result.add(storeName + " " + storeSubName);
        }
        result.add(storeAddress);
        return result;
    }

}