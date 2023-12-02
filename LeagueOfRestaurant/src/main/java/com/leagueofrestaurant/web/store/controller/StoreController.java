package com.leagueofrestaurant.web.store.controller;

import com.leagueofrestaurant.web.store.dto.ResponseStoreDto;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import com.leagueofrestaurant.web.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/home")
    public String test() {
        return "success";
    }

    @GetMapping("/store/{id}")
    public ResponseStoreDto getStoreById(@PathVariable("id") Long storeId) {
        return storeService.getStoreById(storeId);
    }

    @GetMapping("/stores")
    public List<ResponseStoreDto> getAllStores(HttpServletResponse response) {
//        response.setHeader("Access-Control-Allow-Origin", "*");
        return storeService.getAllStores();
    }

    @GetMapping("/stores/condition")
    public List<ResponseStoreDto> getStoreListByCondition(@ModelAttribute StoreSearchCondition condition) {
        return storeService.getStoreListByCondition(condition);
    }

    /**
     * @param city
     * @param pageable page와 size 파라미터로 입력
     * @return
     */
    @GetMapping("/stores/rank/{city}")
    public List<ResponseStoreDto> getStoreRankByCity(@PathVariable String city, Pageable pageable) {
        return storeService.getStoreRankByCity(city, pageable);
    }

    @GetMapping("/stores/map/{city}")
    public List<ResponseStoreDto> getStoreListByCity(@PathVariable String city) {
        return storeService.getStoreListByCity(city);
    }

}
