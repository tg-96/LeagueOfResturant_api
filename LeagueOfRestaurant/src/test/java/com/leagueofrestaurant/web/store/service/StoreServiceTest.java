package com.leagueofrestaurant.web.store.service;

import com.leagueofrestaurant.web.store.domain.Address;
import com.leagueofrestaurant.web.store.dto.StoreDto;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
class StoreServiceTest {
    @Autowired
    private StoreService storeService;

    @Test
    @DisplayName("스토어 생성")
    public void createStore() {
        StoreDto storeDto1 = new StoreDto("yul",
                new Address("a", "k", "l"),
                null
        );
        storeService.createStore(storeDto1);
        List<StoreDto> stores = storeService.getAllStores();
//        assertThat(stores.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("아이디로 스토어 조회")
    public void getStoreById() {
        StoreDto store = storeService.getStoreById(3L);
        assertThat(store.getName()).isEqualTo("aa");
    }

    @Test
    @DisplayName("모든 가게 조회")
    public void getAllStores() {
        List<StoreDto> stores = storeService.getAllStores();
        assertThat(stores.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("스토어 정보 변경")
    public void updateStore() {
        StoreDto storeDto = new StoreDto("jeong", null, null);
        storeService.updateStore(3L, storeDto);
        StoreDto store = storeService.getStoreById(3L);
        assertThat(store.getName()).isEqualTo("jeong");
    }

    @Test
    @DisplayName("조건에 따른 가게 검색")
    public void getStoreListByCondition() {
        StoreSearchCondition cond1 = new StoreSearchCondition("jeong", null);
        StoreSearchCondition cond2 = new StoreSearchCondition("jeong", new Address("a", "b", null));
        List<StoreDto> storeDtoList1 = storeService.getStoreListByCondition(cond1);
        List<StoreDto> storeDtoList2 = storeService.getStoreListByCondition(cond2);
//        assertThat(storeDtoList1.size()).isEqualTo(2);
        assertThat(storeDtoList2.size()).isEqualTo(1);
    }
}