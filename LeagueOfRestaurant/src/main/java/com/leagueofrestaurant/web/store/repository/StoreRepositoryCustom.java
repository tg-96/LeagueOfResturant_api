package com.leagueofrestaurant.web.store.repository;

import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreRepositoryCustom {
    public List<Store> findStoreListByCondition(StoreSearchCondition condition);

    public List<Store> findTopSCore10ByCity(String city);

    public List<String> findAllCity();

    Page<Store> findRankListByCity(String city, Pageable pageable);

    public List<Store> findStoreByCity(String city);

}
