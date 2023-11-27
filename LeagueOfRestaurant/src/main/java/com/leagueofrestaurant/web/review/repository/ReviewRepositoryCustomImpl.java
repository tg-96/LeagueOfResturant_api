package com.leagueofrestaurant.web.review.repository;

import com.leagueofrestaurant.web.review.domain.Review;
import com.leagueofrestaurant.web.review.dto.ReviewContent;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.leagueofrestaurant.web.review.domain.QReview.review;

public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory query;

    public ReviewRepositoryCustomImpl(EntityManager entityManager) {
        this.query = new JPAQueryFactory(entityManager);
    }
}
