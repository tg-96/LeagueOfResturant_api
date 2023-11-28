package com.leagueofrestaurant.web.member.repository;

import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.dto.MemberSearchCondition;

import java.util.List;

public interface MemberRepositoryCustom {
    Member findMemberByPhoneNumber(String phoneNumber);
    List<Member> findByCondition(MemberSearchCondition condition);
}
