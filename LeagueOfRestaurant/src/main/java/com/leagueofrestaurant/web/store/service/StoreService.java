package com.leagueofrestaurant.web.store.service;

import com.leagueofrestaurant.web.exception.ErrorCode;
import com.leagueofrestaurant.web.exception.LORException;
import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.dto.RequestStoreDto;
import com.leagueofrestaurant.web.store.dto.ResponseStoreDto;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import com.leagueofrestaurant.web.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;

    public ResponseStoreDto getStoreById(Long storeId) {
        Store store = storeRepository.findById(storeId).get();
        ResponseStoreDto storeDto = new ResponseStoreDto(store.getId(),store.getName(), store.getAddress(),store.getCity() ,store.getImg());
        return storeDto;
    }

    public List<ResponseStoreDto> getAllStores() {
        List<Store> storeList = storeRepository.findAll();
        return getStoreDtoList(storeList);
    }

    public List<ResponseStoreDto> getStoreListByCondition(StoreSearchCondition condition) {
        List<Store> storeList = storeRepository.findStoreListByCondition(condition);
        return getStoreDtoList(storeList);
    }

    @Transactional
    public void updateStore(Long storeId, RequestStoreDto storeDto) {
        Store store = storeRepository.findById(storeId).get();
        store.change(storeDto);
    }

    @Transactional
    public void createStore(RequestStoreDto storeDto) {
        Store store = new Store(
                storeDto.getName(),
                storeDto.getAdress(),
                storeDto.getCity(),
                storeDto.getImg()
        );
        storeRepository.save(store);
    }

    private static List<ResponseStoreDto> getStoreDtoList(List<Store> storeList) {
        try {
            return storeList.stream()
                    .map(s -> new ResponseStoreDto(s.getId(),s.getName(), s.getAddress(), s.getCity(),s.getImg()))
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new LORException(ErrorCode.NO_EXIST_STORE);
        }
    }

}
