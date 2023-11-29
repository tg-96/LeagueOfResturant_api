package com.leagueofrestaurant.web.wish.repository;

import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.wish.domain.Wish;
import java.util.List;

public interface WishRepositoryCustom {
    Wish getWishState(Long memberId, Long storeId);
    List<Store> findAllByMemberId(Long memberId);
}
