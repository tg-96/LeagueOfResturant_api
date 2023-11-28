package com.leagueofrestaurant.web.store.repository;

import com.leagueofrestaurant.web.store.domain.Address;
import com.leagueofrestaurant.web.store.domain.QStore;
import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.leagueofrestaurant.web.member.domain.QMember.member;
import static com.leagueofrestaurant.web.store.domain.QStore.*;
import static org.springframework.util.StringUtils.concatenateStringArrays;
import static org.springframework.util.StringUtils.hasText;

public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {
    private final JPAQueryFactory query;
    public StoreRepositoryCustomImpl(EntityManager em){
        this.query = new JPAQueryFactory(em);
    }
    @Override
    public List<Store> findStoreListByCondition(StoreSearchCondition condition) {
        return query.selectFrom(store)
                .where(
                        eqName(condition.getName()),
                        eqAddress(condition.getAddress())
                        )
                .fetch();
    }
    private BooleanExpression eqName(String nameCond){
        return hasText(nameCond) ? store.name.containsIgnoreCase(nameCond) : null;
    }
    private BooleanExpression eqAddress(Address address){
        return address != null ?
                store.address.city.containsIgnoreCase(address.getCity())
                        .and(store.address.district.containsIgnoreCase(address.getDistrict()))
                        .and(store.address.street.containsIgnoreCase(address.getStreet())) : null;
    }
}
