package com.leagueofrestaurant.web.wish.service;

import com.leagueofrestaurant.web.member.domain.Gender;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.domain.MemberType;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.dto.ResponseStoreDto;
import com.leagueofrestaurant.web.store.repository.StoreRepository;
import com.leagueofrestaurant.web.wish.domain.Wish;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class WishServiceTest {
    @Autowired
    private WishService wishService;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void 멤버추가() {
        List<Member> memberList = new ArrayList<>();

        Member member1 = new Member("John Doe", "1234567890", "password1", Gender.MALE, LocalDate.of(1990, 1, 1), MemberType.USER);
        Member member2 = new Member("Jane Doe", "9876543210", "password2", Gender.FEMALE, LocalDate.of(1995, 5, 5), MemberType.USER);
        Member member3 = new Member("Alice", "1112223333", "password3", Gender.FEMALE, LocalDate.of(1985, 10, 10), MemberType.USER);
        Member member4 = new Member("Bob", "4445556666", "password4", Gender.MALE, LocalDate.of(1980, 3, 15), MemberType.USER);
        Member member5 = new Member("Charlie", "7778889999", "password5", Gender.MALE, LocalDate.of(2000, 8, 20), MemberType.USER);

        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);
        memberList.add(member4);
        memberList.add(member5);

        memberRepository.saveAllAndFlush(memberList);
    }
    @Test
    @Rollback(value = false)
    public void 가게추가(){
        List<Store> storeList = new ArrayList<>();

        Store store1 = new Store("Restaurant A", "123 Main St", "City A", "image1.jpg");
        Store store2 = new Store("Cafe B", "456 Oak St", "City B", "image2.jpg");
        Store store3 = new Store("Fast Food C", "789 Maple St", "City C", "image3.jpg");
        Store store4 = new Store("Pizzeria D", "101 Pine St", "City D", "image4.jpg");
        Store store5 = new Store("Bakery E", "202 Cedar St", "City E", "image5.jpg");

        storeList.add(store1);
        storeList.add(store2);
        storeList.add(store3);
        storeList.add(store4);
        storeList.add(store5);

        storeRepository.saveAllAndFlush(storeList);
    }

    @Test
    @DisplayName("wish 추가")
    public void addWish_getWishListByMemberId(){
        Member member = memberRepository.findMemberByPhoneNumber("1234567890");
        Store store = storeRepository.findById(18L).get();
        wishService.addWish(member.getId(), store.getId());
        List<ResponseStoreDto> wishList = wishService.getWishListByMemberId(13L);
        assertThat(wishList.get(0).getName()).isEqualTo("Restaurant A");
    }


    @Test
    @DisplayName("wish 삭제")
    @Rollback(value = false)
    public void delete(){
        wishService.deleteWish(3L);
    }


}