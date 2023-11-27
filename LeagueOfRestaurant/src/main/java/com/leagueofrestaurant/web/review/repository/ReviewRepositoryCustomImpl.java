package com.leagueofrestaurant.web.review.repository;

import com.leagueofrestaurant.web.review.domain.Review;
import com.leagueofrestaurant.web.review.dto.ReviewContent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import static com.leagueofrestaurant.web.review.domain.QReview.review;

public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public List<Review> findAllByMemberId(Long memberId) {
        return queryFactory
                .selectFrom(review)
                .where(review.member.id.eq(memberId))
                .fetch();
    }

    @Override
    public List<Review> findAllByStoreId(Long storeId) {
        return queryFactory
                .selectFrom(review)
                .where(review.store.id.eq(storeId))
                .fetch();
    }

}
