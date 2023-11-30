package com.leagueofrestaurant.web.store.repository;

import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;

import java.util.List;

public interface StoreRepositoryCustom {
    public List<Store> findStoreListByCondition(StoreSearchCondition condition);
    /**
     * 스토어 랭킹
     * 해당 시 조건인 가게 별점 순으로 조회
     *
     *
     */

}
