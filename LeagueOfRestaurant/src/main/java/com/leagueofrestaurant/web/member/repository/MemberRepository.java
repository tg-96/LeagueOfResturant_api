package com.leagueofrestaurant.web.member.repository;

import com.leagueofrestaurant.web.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom, QuerydslPredicateExecutor {
    @Override
    List<Member> findAll();

    @Override
    Optional<Member> findById(Long memberId);
}
