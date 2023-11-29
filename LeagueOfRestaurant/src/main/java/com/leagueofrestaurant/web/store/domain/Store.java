package com.leagueofrestaurant.web.store.domain;

import com.leagueofrestaurant.web.store.dto.RequestStoreDto;
import com.leagueofrestaurant.web.store.dto.ResponseStoreDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 가게 랭킹 산정 방식
 * 기존: 해당 storeId를 가지는 모든 리뷰들의 별점 조회 후 총합 / 리뷰 수 = 평균 구해서 랭킹
 * 의견: 새로운 랭킹 테이블 만들어서, 해당 store의 별점 평균 저장. 리뷰가 추가 될때마다 업데이트
 * 테이블 컬럼 내용: storeId, 별점
 * <p>
 * 가게 카테고리 어떤걸로 할지 정해야 함.
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {
    @Id
    @GeneratedValue
    @Column(name = "store_id")
    private long id;
    private String name;

    private String address;
    private String city;
    private String img;

    public Store(String name, String address, String city, String img) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.img = img;
    }

    public void change(RequestStoreDto storeDto){
        if(storeDto.getName() != null){
            this.name = storeDto.getName();
        }
        if(storeDto.getAdress() != null){
            this.address = storeDto.getAdress();
        }
        if(storeDto.getImg() != null){
            this.img = storeDto.getImg();
        }
        if(storeDto.getCity() != null){
            this.city = storeDto.getCity();
        }
    }
}

