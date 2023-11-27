package com.leagueofrestaurant.web.review.repository;

import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.review.domain.Review;
import com.leagueofrestaurant.web.review.dto.ReviewContent;
import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findAllByMemberId(Long member_Id);
    List<Review> findAllByStoreId(Long store_Id);
}
