package com.leagueofrestaurant.web.wish.controller;

import com.leagueofrestaurant.web.store.dto.ResponseStoreDto;
import com.leagueofrestaurant.web.wish.domain.Wish;
import com.leagueofrestaurant.web.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.leagueofrestaurant.web.common.SessionKey.LOGIN_SESSION_KEY;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class WishController {
    private final WishService wishService;

    @PutMapping("/wish/change/{storeId}")
    public ResponseEntity<Void> changeWishState(HttpSession session,
                                                @PathVariable("storeId") Long storeId) {
        wishService.changeWishState((Long) session.getAttribute(LOGIN_SESSION_KEY), storeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wishes")
    public List<ResponseStoreDto> getWishListByMemberId(HttpSession session) {
        return wishService.getWishListByMemberId((Long) session.getAttribute(LOGIN_SESSION_KEY));
    }

    @GetMapping("/wish/state/{storeId}")
    public boolean getWishState(HttpSession session, @PathVariable Long storeId) {
        Wish wish = wishService.getWish((Long) session.getAttribute(LOGIN_SESSION_KEY), storeId);
        if (wish != null) return true;
        return false;
    }

}
