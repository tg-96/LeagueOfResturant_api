package com.leagueofrestaurant.web.store.service;

import com.leagueofrestaurant.web.exception.ErrorCode;
import com.leagueofrestaurant.web.exception.LORException;
import com.leagueofrestaurant.web.review.repository.ReviewRepository;
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
    private final ReviewRepository reviewRepository;

    public ResponseStoreDto getStoreById(Long storeId) {
        Store store = storeRepository.findById(storeId).get();
        ResponseStoreDto storeDto = new ResponseStoreDto(store.getId(), store.getName(), store.getAddress(), store.getCity(), store.getImg());
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

    /**
     * 리뷰 추가 될때 스토어의 평균 별점 계산후 수정
     */
    @Transactional
    public void calculateRating(Long storeId, float rating) {
        Store store = storeRepository.findById(storeId).get();
        Long count = reviewRepository.countByStoreId(storeId);
        //(가게 평균별점*리뷰수 +새로운 리뷰별점)/(리뷰수+1) = 새로운 평균별점
        float newRating = (store.getRating() * count + rating) / (count + 1);
        store.changeRating(newRating);
    }

    private static List<ResponseStoreDto> getStoreDtoList(List<Store> storeList) {
        try {
            return storeList.stream()
                    .map(s -> new ResponseStoreDto(s.getId(), s.getName(), s.getAddress(), s.getCity(), s.getImg()))
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new LORException(ErrorCode.NO_EXIST_STORE);
        }
    }

}
