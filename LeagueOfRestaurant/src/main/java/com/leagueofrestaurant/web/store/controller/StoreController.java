package com.leagueofrestaurant.web.store.controller;

import com.leagueofrestaurant.web.store.dto.ResponseStoreDto;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import com.leagueofrestaurant.web.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/store/{id}")
    public ResponseStoreDto getStoreById(@PathVariable("id")Long storeId){
        return storeService.getStoreById(storeId);
    }

    @GetMapping("/stores")
    public List<ResponseStoreDto> getAllStores(){
        return storeService.getAllStores();
    }

    @GetMapping("/stores/condition")
    public List<ResponseStoreDto> getStoreListByCondition(@ModelAttribute StoreSearchCondition condition){
        return storeService.getStoreListByCondition(condition);
    }
    @GetMapping("/stores/rank/{city}")
    public List<ResponseStoreDto> getStoreRankByCity(@PathVariable String city){
        return storeService.getStoreRankByCity(city);
    }
}
