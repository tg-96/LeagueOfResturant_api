package com.leagueofrestaurant.web.member.dto;

import lombok.Getter;

/**
 * 유저 검색 조건 Dto
 *
 */
@Getter
public class MemberSearchCondition {
    private String name;
    private String phoneNumber;
    public static MemberSearchCondition create(String name,String phoneNumber){
        MemberSearchCondition memberSearchCondition = new MemberSearchCondition();
        memberSearchCondition.name = name;
        memberSearchCondition.phoneNumber = phoneNumber;
        return memberSearchCondition;
    }
}
