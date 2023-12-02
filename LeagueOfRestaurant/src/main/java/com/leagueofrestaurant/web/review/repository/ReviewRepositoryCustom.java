package com.leagueofrestaurant.web.review.repository;

import com.leagueofrestaurant.web.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findAllByMemberId(Long member_Id);
    Page<Review> findAllByStoreId(Long store_Id, Pageable pageable);
    List<Review> findByMemberIdAndStoreId(Long member_Id, Long store_Id, String season);
    Long countByStoreIdAndSeason(Long storeId,String season);
}
