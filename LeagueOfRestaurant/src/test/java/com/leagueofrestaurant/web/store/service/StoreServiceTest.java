package com.leagueofrestaurant.web.store.service;

import com.leagueofrestaurant.web.store.domain.Address;
import com.leagueofrestaurant.web.store.dto.StoreDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class StoreServiceTest {
    @Autowired private StoreService storeService;

    @Test
    @DisplayName("스토어 생성")
    public void createStore(){
        StoreDto storeDto1 = new StoreDto("aa",
                new Address("a","b","c"),
                null
                );

        storeService.createStore(storeDto1);

    }
    @Test
    @DisplayName("아이디로 스토어 조회")
    public void getStoreById() {


    }
}