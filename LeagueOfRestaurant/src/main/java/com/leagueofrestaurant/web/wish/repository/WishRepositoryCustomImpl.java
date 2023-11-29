package com.leagueofrestaurant.web.wish.repository;

import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.wish.domain.Wish;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import java.util.List;

import static com.leagueofrestaurant.web.store.domain.QStore.store;
import static com.leagueofrestaurant.web.wish.domain.QWish.wish;

public class WishRepositoryCustomImpl implements WishRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public WishRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Wish getWishState(Long memberId, Long storeId) {
        return queryFactory
                .select(wish)
                .from(wish)
                .where(wish.store.id.eq(storeId).and(wish.member.id.eq(memberId)))
                .fetchOne();
    }

    @Override
    public List<Store> findAllByMemberId(Long member_Id) {
        return queryFactory
                .select(wish.store)
                .from(wish)
                .leftJoin(wish.store,store)
                .where(wish.member.id.eq(member_Id))
                .fetch();
    }
}
