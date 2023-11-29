package com.leagueofrestaurant.web.wish.controller;

import com.leagueofrestaurant.web.store.dto.ResponseStoreDto;
import com.leagueofrestaurant.web.wish.domain.Wish;
import com.leagueofrestaurant.web.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;

    @PutMapping("/wish/change/{memberId}/{storeId}")
    public ResponseEntity<Void> changeWishState(@PathVariable("memberId") Long memberId,
                                                @PathVariable("storeId") Long storeId) {
        wishService.changeWishState(memberId, storeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wishes/{memberId}")
    public List<ResponseStoreDto> getWishListByMemberId(@PathVariable("memberId") Long memberId) {
        return wishService.getWishListByMemberId(memberId);
    }

    @GetMapping("/wish/state/{memberId}/{storeId}")
    public boolean getWishState(@PathVariable Long memberId, @PathVariable Long storeId) {
        Wish wish = wishService.getWish(memberId, storeId);
        if (wish != null) return true;
        return false;
    }

}
