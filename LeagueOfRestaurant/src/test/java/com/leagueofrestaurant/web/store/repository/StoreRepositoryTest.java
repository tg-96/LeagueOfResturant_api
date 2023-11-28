package com.leagueofrestaurant.web.store.repository;

import com.leagueofrestaurant.web.store.domain.Address;
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
        Store store1 = new Store("a가게",
                new Address("성남시", "수정구", "태평동"),
                null);
        Store store2 = new Store("a가게",
                new Address("성남시", "판교", "목현동"),
                null);
        Store store3 = new Store("c가게",
                new Address("성남시", "수정구", "태평동"),
                null);
        Store store4 = new Store("b가게",
                new Address("수원시", "영통구", "권선동"),
                null);

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
        StoreSearchCondition cond2 = new StoreSearchCondition("a가게", new Address("성남시","수정구","태평동"));
        List<Store> list2 = storeRepository.findStoreListByCondition(cond2);
        Assertions.assertThat(list2.size()).isEqualTo(1);
        //주소 검색
        StoreSearchCondition cond3 = new StoreSearchCondition(null, new Address("성남시","수정구","태평동"));
        List<Store> list3 = storeRepository.findStoreListByCondition(cond3);
        Assertions.assertThat(list3.size()).isEqualTo(2);


    }
}