package com.leagueofrestaurant.web.wish.domain;

import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.store.domain.Store;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * 찜하기
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish {
    @GeneratedValue @Id
    @Column(name = "wish_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public Wish(Member member, Store store) {
        this.member = member;
        this.store = store;
    }
}
