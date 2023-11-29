package com.leagueofrestaurant.web.wish.repository;

import com.leagueofrestaurant.web.member.domain.Gender;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.domain.MemberType;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.repository.StoreRepository;
import com.leagueofrestaurant.web.wish.domain.Wish;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WishRepositoryTest {
    @Autowired
    WishRepository wishRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    MemberRepository memberRepository;

//    Address address = new Address("seoul","3cmd","342");
//    Member member1 = new Member("한규정", "010-3022-1161", "msp214314", Gender.MALE, LocalDate.now(), MemberType.USER);
//    Member member2 = new Member("한투정", "010-3022-1161", "msp214314", Gender.MALE, LocalDate.now(), MemberType.USER);
//    Store store1 = new Store("가게", address, "dfd");
//    Store store2 = new Store("가게2", address, "dfd");

//    @Test
//    public void 찜한가게조회() {
//        memberRepository.saveAndFlush(member1);
//        memberRepository.saveAndFlush(member2);
//        storeRepository.saveAndFlush(store1);
//        storeRepository.saveAndFlush(store2);
//
//        Wish wish1 = new Wish(member1, store1);
//        Wish wish3 = new Wish(member2, store1);
//        Wish wish2 = wishRepository.saveAndFlush(wish1);
//        List<Wish> myWishList = wishRepository.findAllByMemberId(member1.getId());
//        System.out.println(myWishList.get(0));
//        assertThat(myWishList.get(0)).isEqualTo(wish1);
//    }

}