package com.leagueofrestaurant.web.store.repository;

import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.leagueofrestaurant.web.store.domain.QStore.store;
import static org.springframework.util.StringUtils.hasText;

public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {
    private final JPAQueryFactory query;

    public StoreRepositoryCustomImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Store> findStoreListByCondition(StoreSearchCondition condition) {
        return query.selectFrom(store)
                .where(
                        eqName(condition.getName()),
                        eqCity(condition.getCity()),
                        eqAddress(condition.getAddress())
                )
                .fetch();
    }

    @Override
    public Page<Store> findRankListByCity(String city, Pageable pageable) {
        List<Store> content = query.selectFrom(store)
                .where(eqCity(city))
                .orderBy(store.score.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query
                .select(store.count())
                .from(store)
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    @Override
    public List<Store> findStoreByCity(String city) {
        return query.selectFrom(store)
                .where(eqCity(city))
                .fetch();
    }

    public List<Store> findTopSCore10ByCity(String city) {
        return query.selectFrom(store)
                .where(eqCity(city))
                .orderBy(store.score.desc())
                .limit(10)
                .fetch();
    }

    @Override
    public List<String> findAllCity() {
        return query.select(store.city)
                .from(store)
                .distinct()
                .fetch();
    }

    private BooleanExpression eqName(String nameCond) {
        return hasText(nameCond) ? store.name.containsIgnoreCase(nameCond) : null;
    }

    private BooleanExpression eqCity(String city) {
        return hasText(city) ? store.city.containsIgnoreCase(city) : null;
    }

    private BooleanExpression eqAddress(String address) {
        return hasText(address) ? store.address.containsIgnoreCase(address) : null;
    }

}


