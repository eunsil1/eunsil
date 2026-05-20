package com.example.roomfit.repository;

import com.example.roomfit.domain.Report;
import com.example.roomfit.domain.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByStatusOrderByCreatedAtDesc(ReportStatus status);
    //Status - PENDING, PROCESSED, REJECTED 중 하나
    //OrderByCreatedAtDesc - 최근 신고순
    //용도 - 대기 신고 목록(처리용)
    //관리자 대시보드 /admin 대기 중 신고 목록

    //countByStatus - 대기 신고 갯수
    long countByStatus(ReportStatus status);
}
