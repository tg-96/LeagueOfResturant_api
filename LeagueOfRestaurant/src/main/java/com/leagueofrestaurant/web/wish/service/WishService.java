package com.leagueofrestaurant.web.wish.service;

import com.leagueofrestaurant.web.exception.ErrorCode;
import com.leagueofrestaurant.web.exception.LORException;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.repository.StoreRepository;
import com.leagueofrestaurant.web.wish.domain.Wish;
import com.leagueofrestaurant.web.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WishService {
    private final WishRepository wishRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    @Transactional
    public void addWish(Long memberId,Long storeId){
        try {
            Member member = memberRepository.findById(memberId).get();
            Store store = storeRepository.findById(storeId).get();
            Wish wish = new Wish(member, store);
            wishRepository.save(wish);
        }catch (Exception e){
            throw new LORException(ErrorCode.WRONG_MEMBER_OR_STORE_ID);
        }
    }


}
