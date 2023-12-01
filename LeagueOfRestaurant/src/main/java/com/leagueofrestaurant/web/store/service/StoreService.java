package com.leagueofrestaurant.web.store.service;

import com.leagueofrestaurant.web.common.CommonService;
import com.leagueofrestaurant.web.exception.ErrorCode;
import com.leagueofrestaurant.web.exception.LORException;
import com.leagueofrestaurant.web.review.repository.ReviewRepository;
import com.leagueofrestaurant.web.store.domain.Store;
import com.leagueofrestaurant.web.store.dto.RequestStoreDto;
import com.leagueofrestaurant.web.store.dto.ResponseStoreDto;
import com.leagueofrestaurant.web.store.dto.StoreSearchCondition;
import com.leagueofrestaurant.web.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final CommonService commonService;

    public ResponseStoreDto getStoreById(Long storeId) {
        Store store = storeRepository.findById(storeId).get();
        ResponseStoreDto storeDto = new ResponseStoreDto(store.getId(), store.getName(), store.getAddress(), store.getCity(), store.getImg());
        return storeDto;
    }

    public List<ResponseStoreDto> getAllStores() {
        List<Store> storeList = storeRepository.findAll();
        return getStoreDtoList(storeList);
    }

    public List<ResponseStoreDto> getStoreRankByCity(String city, Pageable pageable) {
        Page<Store> storeList = storeRepository.findRankListByCity(city,pageable);
        return getStoreDtoList(storeList.getContent());
    }

    public List<ResponseStoreDto> getStoreListByCity(String city){
        List<Store> storeList = storeRepository.findStoreByCity(city);
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
                storeDto.getAddress(),
                storeDto.getCity(),
                storeDto.getImg()
        );
        storeRepository.save(store);
    }

    /**
     * 시즌이 끝나면, store의 rating과 score를 초기화 한다.
     */
    @Transactional
    public void initRank() {
            List<Store> storeList = storeRepository.findAll();
            Iterator<Store> iter = storeList.iterator();
            while (iter.hasNext()) {
                Store store = iter.next();
                store.changeScore(0);
                store.changeRating(0);
            }

    }

    /**
     * 리뷰 추가 될때 스토어의 평균 별점 계산후 수정
     * 리뷰를 추가하면 리뷰가 먼저 추가된 값이 count로 들어옴.
     */
    @Transactional
    public void calculateRating(Store store, float rating) {
        Long count = reviewRepository.countByStoreIdAndSeason(store.getId(), commonService.getSeason());
        //(가게 평균별점*리뷰수 +새로운 리뷰별점)/(리뷰수+1) = 새로운 평균별점
        float newRating = (store.getRating() * (count - 1) + rating) / count;
        store.changeRating(newRating);
    }

    /**
     * score = 별점평균*0.7+리뷰수 점수*0.3
     * 총 1000점 만점 = 700 +300
     * 리뷰 1개당 2점
     */
    @Transactional
    public void calculateScore(Store store) {
        //별점 5점 만점일때, 총점 700점으로 변경
        float rating = store.getRating();
        float ratingScore = 700 * rating / 5;

        //리뷰수 점수 : 1개당 2점,최대 300점
        Long reviewCount = reviewRepository.countByStoreIdAndSeason(store.getId(), commonService.getSeason());
        float reviewScore = reviewCount * 2;
        if (reviewScore >= 300) reviewScore = 300;
        //총 점수
        float totalScore = ratingScore + reviewScore;
        //소수 첫째자리까지만 score 나타냄.
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        float formattedTotalScore = Float.parseFloat(decimalFormat.format(totalScore));
        store.changeScore(formattedTotalScore);
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
