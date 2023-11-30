package com.leagueofrestaurant.web.report.service;

import com.leagueofrestaurant.web.common.Status;
import com.leagueofrestaurant.web.exception.ErrorCode;
import com.leagueofrestaurant.web.exception.LORException;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import com.leagueofrestaurant.web.report.domain.Report;
import com.leagueofrestaurant.web.report.dto.ReportDto;
import com.leagueofrestaurant.web.report.repository.ReportRepository;
import com.leagueofrestaurant.web.review.domain.Review;
import com.leagueofrestaurant.web.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    //신고 생성
    @Transactional
    public void createReport(ReportDto reportDto) {
        Optional<Review> reviewOptional = reviewRepository.findById(reportDto.getReview_id());
        Optional<Member> memberOptional = memberRepository.findById(reportDto.getMember_id());
        if(!(reviewOptional.isPresent())){
            throw new LORException(ErrorCode.NOT_EXIST_REVIEW);
        }
        else if(!(memberOptional.isPresent())){
            throw new LORException(ErrorCode.NOT_EXIST_MEMBER);
        }
        else {
            Review review = reviewOptional.get();
            Member member = memberOptional.get();
            Report newReport = new Report(reportDto.getType(), reportDto.getContent(), member, review);
            reportRepository.save(newReport);
        }
    }

    //모든 신고내역 조회
    public List<ReportDto> getAllReports() {
        List<Report> reports = reportRepository.findAll();

        return reports.stream()
                .map(report -> new ReportDto(report.getType(), report.getContent(), report.getStatus(), report.getMember().getId(), report.getReview().getId()))
                .collect(Collectors.toList());
    }

    // 특정 신고내역 조회
    public ReportDto getReport(Long reportId) {
        Optional<Report> optionalReport = reportRepository.findById(reportId);

        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            return new ReportDto(report.getType(), report.getContent(), report.getStatus(), report.getMember().getId(), report.getReview().getId());
        } else {
            throw new LORException(ErrorCode.NOT_EXIST_REPORT);
        }
    }

    // 특정 회원의 신고내역 조회
    public List<ReportDto> getReportsByMemberId(Long memberId) {
        List<Report> reports = reportRepository.findAllByMemberId(memberId);

        return reports.stream()
                .map(report -> new ReportDto(report.getType(), report.getContent(), report.getStatus(), report.getMember().getId(), report.getReview().getId()))
                .collect(Collectors.toList());
    }

    // 처리상태로 신고내역 조회
    public List<ReportDto> getReportsByStatus(Status status) {
        List<Report> reports = reportRepository.findAllByStatus(status);

        return reports.stream()
                .map(report -> new ReportDto(report.getType(), report.getContent(), report.getStatus(), report.getMember().getId(), report.getReview().getId()))
                .collect(Collectors.toList());
    }

}
