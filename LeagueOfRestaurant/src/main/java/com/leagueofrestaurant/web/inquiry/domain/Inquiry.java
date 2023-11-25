package com.leagueofrestaurant.web.inquiry.domain;

import com.leagueofrestaurant.web.common.Status;
import com.leagueofrestaurant.web.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Inquiry {
    @GeneratedValue
    @Id
    @Column(name = "inquiry_id")
    private long id;
    private String title;
    private String content;
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Inquiry(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.status = Status.PROCESSING;
        this.member = member;
    }

    public void statusChange(Status status) {
        this.status = status;
    }

}
