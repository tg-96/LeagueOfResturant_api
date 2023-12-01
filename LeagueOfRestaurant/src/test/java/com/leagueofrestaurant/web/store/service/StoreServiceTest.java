package com.leagueofrestaurant.web.store.service;

import com.leagueofrestaurant.web.member.domain.Gender;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.domain.MemberType;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import com.leagueofrestaurant.web.review.dto.ReceiptInfo;
import com.leagueofrestaurant.web.review.dto.ReviewContent;
import com.leagueofrestaurant.web.review.service.ReviewService;
import com.leagueofrestaurant.web.store.dto.RequestStoreDto;
import com.leagueofrestaurant.web.store.dto.ResponseStoreDto;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
class StoreServiceTest {
    @Autowired
    private StoreService storeService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("스토어 생성")
    public void createStore() {
        RequestStoreDto storeDto1 = new RequestStoreDto("aa", "aa", "a", null);
        storeService.createStore(storeDto1);
        List<ResponseStoreDto> stores = storeService.getAllStores();
//        assertThat(stores.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("아이디로 스토어 조회")
    public void getStoreById() {
        ResponseStoreDto store = storeService.getStoreById(3L);
//        assertThat(store.getName()).isEqualTo("aa");
    }

    @Test
    @DisplayName("모든 가게 조회")
    public void getAllStores() {
        List<ResponseStoreDto> stores = storeService.getAllStores();
//        assertThat(stores.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("스토어 정보 변경")
    public void updateStore() {
        RequestStoreDto storeDto = new RequestStoreDto("jeong", "aaa", "aa", null);
        storeService.updateStore(3L, storeDto);
        ResponseStoreDto store = storeService.getStoreById(3L);
//        assertThat(store.getName()).isEqualTo("jeong");
    }

    @Test
    @DisplayName("조건에 따른 가게 검색")
    public void getStoreListByCondition() {
        StoreSearchCondition cond1 = new StoreSearchCondition("jeong", null, "");
        StoreSearchCondition cond2 = new StoreSearchCondition("jeong", "aa", "");
        List<ResponseStoreDto> storeDtoList1 = storeService.getStoreListByCondition(cond1);
        List<ResponseStoreDto> storeDtoList2 = storeService.getStoreListByCondition(cond2);
//        assertThat(storeDtoList1.size()).isEqualTo(2);
//        assertThat(storeDtoList2.size()).isEqualTo(1);
    }


}