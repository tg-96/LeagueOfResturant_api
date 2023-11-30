package com.leagueofrestaurant.web.review.domain;

import com.leagueofrestaurant.web.common.BaseTimeEntity;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.store.domain.Store;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private long id;
    @NotNull(message = "평점은 null 일 수 없습니다.")
    private Integer ratingPoint;
    @NotBlank(message = "content를 작성해야 합니다.")
    private String content;
    private String season;
    private String img;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    /**
     * 리뷰 생성
     */
    public Review(int ratingPoint, String content, String season, String img,
                  Member member, Store store) {
        this.ratingPoint = ratingPoint;
        this.content = content;
        this.season = season;
        this.img = img;
        this.member = member;
        this.store = store;
    }

    /**
     * 리뷰 수정
     */
    public void changeRatingPoint(Integer ratingPoint) {
        this.ratingPoint = ratingPoint;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeSeason(String season){
        this.season = season;
    }

    public void changeImg(String img) {
        this.img = img;
    }

    public void memberToNull(){
        this.member = null;
    }

}

