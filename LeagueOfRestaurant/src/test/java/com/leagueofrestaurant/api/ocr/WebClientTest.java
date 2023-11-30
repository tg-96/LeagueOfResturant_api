package com.leagueofrestaurant.api.ocr;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WebClientTest {
    @Autowired
    OcrService ocrService = new OcrService();
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void api테스트() throws IOException {
//        String response = ocrService.getReceiptJSON("/Users/hangyujeong/Desktop/영수증테스트/tom.png");
//
//        // JSON 응답을 JsonNode로 파싱
//        JsonNode jsonNode = objectMapper.readTree(response);
//        List<String> a = ocrService.getReceiptInfo(jsonNode);
//        System.out.println(jsonNode);
    }


}