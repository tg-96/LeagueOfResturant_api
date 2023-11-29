package com.leagueofrestaurant.web.store.repository;

import com.leagueofrestaurant.web.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface StoreRepository extends JpaRepository<Store,Long>,StoreRepositoryCustom, QuerydslPredicateExecutor {
    @Override
    Optional<Store> findById(Long storeId);
    @Override
    List<Store> findAll();
}
