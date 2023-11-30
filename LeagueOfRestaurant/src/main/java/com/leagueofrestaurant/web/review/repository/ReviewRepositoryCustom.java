package com.leagueofrestaurant.web.review.repository;

import com.leagueofrestaurant.web.review.domain.Review;
import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findAllByMemberId(Long memberId);
    List<Review> findAllByStoreId(Long storeId);
    Long countByStoreId(Long storeId);
}
