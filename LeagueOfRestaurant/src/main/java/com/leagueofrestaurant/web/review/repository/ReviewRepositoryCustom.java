package com.leagueofrestaurant.web.review.repository;

import com.leagueofrestaurant.web.review.domain.Review;
import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findAllByMemberId(Long member_Id);
    List<Review> findAllByStoreId(Long store_Id);
    List<Review> findByMemberIdAndStoreId(Long member_Id, Long store_Id, String season);
    Long countByStoreIdAndSeason(Long storeId,String season);
}
