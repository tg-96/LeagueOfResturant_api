package com.leagueofrestaurant.web.store.repository;

import com.leagueofrestaurant.web.store.domain.Address;
import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.leagueofrestaurant.web.store.domain.QAddress.address;
import static com.leagueofrestaurant.web.store.domain.QStore.store;
import static org.springframework.util.StringUtils.hasText;

public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {
    private final JPAQueryFactory query;

    public StoreRepositoryCustomImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Store> findStoreListByCondition(StoreSearchCondition condition) {
        Address address = condition.getAddress() != null ? condition.getAddress() : null;
        return query.selectFrom(store)
                .where(
                        eqName(condition.getName()),
                        address != null ? eqCity(address.getCity()) : null,
                        address != null ? eqDistrict(address.getDistrict()) : null,
                        address != null ? eqStreet(address.getStreet()) : null
                )
                .fetch();
    }

    private BooleanExpression eqName(String nameCond) {
        return hasText(nameCond) ? store.name.containsIgnoreCase(nameCond) : null;
    }

    private BooleanExpression eqCity(String city) {
        return hasText(city) ? address.city.containsIgnoreCase(city) : null;
    }

    private BooleanExpression eqDistrict(String district) {
        return hasText(district) ? address.district.containsIgnoreCase(district) : null;
    }

    private BooleanExpression eqStreet(String street) {
        return hasText(street) ? address.street.containsIgnoreCase(street) : null;
    }

}
