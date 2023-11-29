package com.leagueofrestaurant.web.store.service;

import com.leagueofrestaurant.web.exception.ErrorCode;
import com.leagueofrestaurant.web.exception.LORException;
import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.dto.StoreDto;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import com.leagueofrestaurant.web.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;
    public StoreDto getStoreById(Long storeId){
        Store store = storeRepository.findById(storeId).get();
        StoreDto storeDto = new StoreDto(store.getName(),store.getAddress(),store.getImg());
        return storeDto;
    }

    public List<StoreDto> getAllStores(){
        List<Store> storeList = storeRepository.findAll();
        return getStoreDtoList(storeList);
    }

    public List<StoreDto> getStoreListByCondition(StoreSearchCondition condition){
        List<Store> storeList = storeRepository.findStoreListByCondition(condition);
        return getStoreDtoList(storeList);
    }

    @Transactional
    public void updateStore(Long storeId, StoreDto storeDto){
        Store store = storeRepository.findById(storeId).get();
        store.change(storeDto);
    }

    private static List<StoreDto> getStoreDtoList(List<Store> storeList) {
        try{
            return storeList.stream()
                    .map(s -> new StoreDto(s.getName(), s.getAddress(), s.getImg()))
                    .collect(Collectors.toList());
        }catch (NullPointerException e){
            throw new LORException(ErrorCode.NO_EXIST_STORE);
        }
    }

}
