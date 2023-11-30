package com.leagueofrestaurant.web.report.repository;

import com.leagueofrestaurant.web.common.Status;
import com.leagueofrestaurant.web.report.domain.Report;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import static com.leagueofrestaurant.web.report.domain.QReport.report;

public class ReportRepositoryCustomImpl implements ReportRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ReportRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Report> findAllByMemberId(Long memberId) {
        return queryFactory
                .selectFrom(report)
                .where(report.member.id.eq(memberId))
                .fetch();
    }

    @Override
    public List<Report> findAllByStatus(Status status) {
        return queryFactory
                .selectFrom(report)
                .where(report.status.eq(status))
                .fetch();
    }
}