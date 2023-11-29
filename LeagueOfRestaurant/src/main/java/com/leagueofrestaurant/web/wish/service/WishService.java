package com.leagueofrestaurant.web.wish.service;

import com.leagueofrestaurant.web.exception.ErrorCode;
import com.leagueofrestaurant.web.exception.LORException;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.dto.ResponseStoreDto;
import com.leagueofrestaurant.web.store.repository.StoreRepository;
import com.leagueofrestaurant.web.wish.domain.Wish;
import com.leagueofrestaurant.web.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WishService {
    private final WishRepository wishRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public void addWish(Long memberId, Long storeId) {
        try {
            Member member = memberRepository.findById(memberId).get();
            Store store = storeRepository.findById(storeId).get();
            Wish wish = new Wish(member, store);
            wishRepository.save(wish);
        } catch (Exception e) {
            throw new LORException(ErrorCode.WRONG_MEMBER_OR_STORE_ID);
        }
    }

    @Transactional
    public void deleteWish(Long wishId) {
        wishRepository.deleteById(wishId);
    }

    /**
     * 멤버가 찜한 store 목록
     */
    public List<ResponseStoreDto> getWishListByMemberId(Long memberId) {
        List<Store> wishList = wishRepository.findAllByMemberId(memberId);
        if(wishList.size() == 0){
            throw new LORException(ErrorCode.NO_WISHLIST);
        }
        List<ResponseStoreDto> responseStoreDtoList = wishList.stream()
                .map(w -> new ResponseStoreDto(
                        w.getId(),
                        w.getName(),
                        w.getAddress(),
                        w.getCity(),
                        w.getImg()))
                .collect(Collectors.toList());
        return responseStoreDtoList;
    }

    public Wish getWish(Long memberId, Long storeId) {
        return wishRepository.getWishState(memberId, storeId);
    }

    @Transactional
    public void changeWishState(Long memberId, Long storeId) {
        Wish wish = getWish(memberId, storeId);
        //찜 상태라면 찜 해제
        if (wish != null) {
            deleteWish(wish.getId());
        }
        //찜 상태가 아니라면, 찜하기
        else {
            addWish(memberId, storeId);
        }
    }


}
