package com.leagueofrestaurant.web.review.repository;

import com.leagueofrestaurant.web.review.domain.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
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
    public Page<Review> findAllByStoreId(String season, Long storeId, Pageable pageable) {
        List<Review> content = queryFactory
                .selectFrom(review)
                .where(
                        review.store.id.eq(storeId)
                                .and(review.season.eq(season))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        System.out.println(content);
        Long count = queryFactory
                .select(review.count())
                .from(review)
                .where(review.store.id.eq(storeId))
                .fetchOne();

        return new PageImpl(content, pageable, count);
    }

    @Override
    public List<Review> findByMemberIdAndStoreId(Long memberId, Long storeId, String season) {
        return queryFactory
                .selectFrom(review)
                .where(
                        review.member.id.eq(memberId)
                                .and(review.store.id.eq(storeId))
                                .and(review.season.eq(season))
                )
                .fetch();
    }

    public Long countByStoreIdAndSeason(Long storeId, String season) {
        return queryFactory
                .select(review.count())
                .from(review)
                .where(review.store.id.eq(storeId), review.season.eq(season))
                .fetchOne();
    }

}
