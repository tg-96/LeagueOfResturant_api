package com.leagueofrestaurant.web.member.repository;

import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.dto.MemberSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.leagueofrestaurant.web.member.domain.QMember.member;
import static org.springframework.util.StringUtils.hasText;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory query;

    public MemberRepositoryCustomImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }



    @Override
    public Member findMemberByPhoneNumber(String phoneNumber) {
        return query
                .selectFrom(member)
                .where(member.phoneNumber.eq(phoneNumber))
                .fetchOne();
    }

    @Override
    public List<Member> findByCondition(MemberSearchCondition condition) {
        return query
                .selectFrom(member)
                .where(
                        eqName(condition.getName()),
                        eqPhoneNumber(condition.getPhoneNumber()),
                        member.isDeleted.isFalse()
                )
                .fetch();
    }

    private BooleanExpression eqName(String nameCond) {
        return hasText(nameCond) ? member.name.containsIgnoreCase(nameCond) : null;
    }

    private BooleanExpression eqPhoneNumber(String phoneNumberCond) {
        return hasText(phoneNumberCond) ? member.phoneNumber.containsIgnoreCase(phoneNumberCond) : null;
    }
}
