package com.leagueofrestaurant.web.member.repository;

import com.leagueofrestaurant.web.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom, QuerydslPredicateExecutor {
    @Query("select m from Member m where m.isDeleted = false")
    List<Member> findAll();

    @Query("select m from Member m where m.id = :memberId and m.isDeleted = false")
    Optional<Member> findById(@Param("memberId") Long memberId);
}
