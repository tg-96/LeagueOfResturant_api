package com.leagueofrestaurant.web.report.domain;

import com.leagueofrestaurant.web.common.Status;
import com.leagueofrestaurant.web.review.domain.Review;
import com.leagueofrestaurant.web.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class
Report {
    @GeneratedValue
    @Id
    @Column(name = "report_id")
    private long id;
    private Type type;
    private String content;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Builder
    public Report(Type type, String content, Member member, Review review) {
        this.type = type;
        this.content = content;
        this.status = Status.PROCESSING;
        this.member = member;
        this.review = review;
    }

    public void changeStatus(Status status) {
        this.status = status;
    }


}
