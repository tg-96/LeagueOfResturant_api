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
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
    private WishRepository wishRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("가게의 wish 상태 확인")
    public void getWishState(){
        Member member1 = new Member("member1", "010-5913-5935", "123", Gender.MALE, LocalDate.of(1996, 05, 28), MemberType.USER);
        Member member2 = new Member("member2", "010-1593-1234", "321", Gender.MALE, LocalDate.of(2000, 04, 07), MemberType.USER);
        Store store1 = new Store("a가게","경기도성남시수정구","성남시",null);
        Store store2 = new Store("b가게","경기도수원시영통구","수원시",null);

        Member memberEntity = memberRepository.saveAndFlush(member1);
        Store storeEntity = storeRepository.saveAndFlush(store2);

        Wish wish = new Wish(memberEntity, storeEntity);
        wishRepository.saveAndFlush(wish);

        Wish wishState = wishRepository.getWishState(memberEntity.getId(), storeEntity.getId());
        //assertThat(wishState.getMember()).isEqualTo(memberEntity);
    }

    @Test
    @DisplayName("멤버가 찜한 모든 스토어 리스트")
    public void findAllByMemberId(){
        Member member = memberRepository.findMemberByPhoneNumber("010-5913-5935");
        Store store2 = new Store("b가게","경기도수원시영통구","수원시",null);
        Store store = storeRepository.saveAndFlush(store2);
        Wish wish = new Wish(member, store);
        wishRepository.saveAndFlush(wish);

        List<Store> wishList = wishRepository.findAllByMemberId(member.getId());
        assertThat(wishList.get(0).getName()).isEqualTo("a가게");
        assertThat(wishList.get(1).getName()).isEqualTo("b가게");
    }
}