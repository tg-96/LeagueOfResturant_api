package com.leagueofrestaurant.domain;

import lombok.*;
import org.apache.catalina.Store;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Review {
    @Id @GeneratedValue
    @Column(name = "review_id")
    private long id;
    private int ratingPoint;
    private String content;
    private String img;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    /**
     * 리뷰 생성
     */
    @Builder
    public Review(@NonNull int ratingPoint,@NonNull String content, String img,
                  @NonNull Member member,@NonNull Store store) {
        this.ratingPoint = ratingPoint;
        this.content = content;
        this.img = img;
        this.member = member;
        this.store = store;
    }

    /**
     * 리뷰 수정
     */
    public void updateReview(Integer ratingPoint, String content, String img) {
        if(ratingPoint != null)
            this.ratingPoint = ratingPoint;
        if(content != null){
            this.content = content;
        }
        if(img != null){
            this.img = img;
        }
    }
}
