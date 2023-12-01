package com.leagueofrestaurant.web.store.repository;

import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class StoreRepositoryTest {
    @Autowired
    StoreRepository storeRepository;

    @Test
    @Rollback(value = false)
    public void 조건별_조회() {
        Store store1 = new Store("a", "aa", "aaa", null);
        Store store2 = new Store("b", "bb", "ccc", null);
        Store store3 = new Store("z", "q", "p", null);
        Store store4 = new Store("hh", "gg", "ff", null);

        List<Store> storeList = new ArrayList<>();
        storeList.add(store1);
        storeList.add(store2);
        storeList.add(store3);
        storeList.add(store4);
        storeRepository.saveAllAndFlush(storeList);

        List<String> allCity = storeRepository.findAllCity();
        System.out.println("----------------");
        for(String city:allCity) {
            System.out.println(city);
        }
    }
}