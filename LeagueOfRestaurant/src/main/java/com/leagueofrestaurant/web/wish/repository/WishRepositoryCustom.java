package com.leagueofrestaurant.web.wish.repository;

import com.leagueofrestaurant.web.wish.domain.Wish;
import java.util.List;

public interface WishRepositoryCustom {
    List<Wish> getWishState(Long member_id, Long store_id);
    List<Wish> findAllByMemberId(Long member_id);
}
