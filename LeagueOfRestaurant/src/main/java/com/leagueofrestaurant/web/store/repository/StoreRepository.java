package com.leagueofrestaurant.web.store.repository;

import com.leagueofrestaurant.web.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface StoreRepository extends JpaRepository<Store,Long> {

}
