package com.leagueofrestaurant.web.store.repository;

import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class StoreRepositoryTest {
    @Autowired
    StoreRepository storeRepository;

    @Test
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
        //이름 검색
        StoreSearchCondition cond1 = new StoreSearchCondition("a가게", null);
        List<Store> list1 = storeRepository.findStoreListByCondition(cond1);
        Assertions.assertThat(list1.size()).isEqualTo(2);
        //주소,이름 검색
        StoreSearchCondition cond2 = new StoreSearchCondition("a가게", "수정구");
        List<Store> list2 = storeRepository.findStoreListByCondition(cond2);
        Assertions.assertThat(list2.size()).isEqualTo(1);
        //주소 검색
        StoreSearchCondition cond3 = new StoreSearchCondition(null, "수정구");
        List<Store> list3 = storeRepository.findStoreListByCondition(cond3);
        Assertions.assertThat(list3.size()).isEqualTo(2);


    }
}