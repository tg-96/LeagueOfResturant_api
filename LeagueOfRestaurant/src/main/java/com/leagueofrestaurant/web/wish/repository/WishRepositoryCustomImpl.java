package com.leagueofrestaurant.web.wish.repository;

import com.leagueofrestaurant.web.wish.domain.Wish;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import java.util.List;
import static com.leagueofrestaurant.web.wish.domain.QWish.wish;

public class WishRepositoryCustomImpl implements WishRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public WishRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Wish> getWishState(Long member_Id, Long store_Id) {
        return queryFactory
                .selectFrom(wish)
                .where(wish.store.id.eq(store_Id))
                .fetch();
    }

    @Override
    public List<Wish> findAllByMemberId(Long member_Id) {
        return queryFactory
                .selectFrom(wish)
                .where(wish.member.id.eq(member_Id))
                .fetch();
    }
}
