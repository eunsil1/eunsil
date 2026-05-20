package com.example.roomfit.service;

import com.example.roomfit.domain.Member;
import com.example.roomfit.domain.Report;
import com.example.roomfit.domain.ReportStatus;
import com.example.roomfit.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final MemberService memberService;

    @Transactional
    public void report(Long reporterId, String targetType, Long targetId, String reason){
        Member reporter = memberService.findById(reporterId); //reporterId - 로그인한 회원 id
        reportRepository.save(Report.builder()
                .reporter(reporter)
                .targetType(targetType) //community_post
                .targetId(targetId) //신고한 글 id
                .reason(reason) //tlsrhtkdb
                .build());
    }//status - 설정하지 않으므로 기본값 PENDING이 됩니다.

    public List<Report> pendingReports(){
        return reportRepository.findByStatusOrderByCreatedAtDesc(ReportStatus.PENDING);
    }
    //PENDING 상태면 최신순으로 조회 - 대시보드 신고 처리 테이블

    @Transactional
    public void process(Long reportId, ReportStatus status, String adminNote){
        Report report = reportRepository.findById(reportId).orElseThrow();
        report.setStatus(status); //PROCESSED 또는 REJECTED
        report.setAdminNote(adminNote); //관리자 메모 (폼에서, 비어 있을 수 있음)
    }

}
