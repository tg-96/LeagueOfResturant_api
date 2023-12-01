package com.leagueofrestaurant.web.store.controller;

import com.leagueofrestaurant.web.store.dto.ResponseStoreDto;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import com.leagueofrestaurant.web.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/store/{id}")
    public ResponseStoreDto getStoreById(@PathVariable("id") Long storeId) {
        return storeService.getStoreById(storeId);
    }

    @GetMapping("/stores")
    public List<ResponseStoreDto> getAllStores() {
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

    @PutMapping("/stores/initRank")
    public ResponseEntity<Void> initRank() {
        storeService.initRank();
        return ResponseEntity.ok().build();
    }
}
