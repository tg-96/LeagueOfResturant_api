package com.leagueofrestaurant.web.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 유저 검색 조건 Dto
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearchCondition {
    private String name;
    private String phoneNumber;
}
