package com.example.roomfit.domain;

public enum ReportStatus {

    PENDING, PROCESSED, REJECTED
}
//신고(Report)가 관리자 손에서 어떤 단계인지 나타냄
//PENDING - 대기, 신고접수됨(아직 처리 안됨) - 기본값
//PROCESSED - 처리완료 - 관리자가 검토 후 조치함(삭제, 경고 등)
//REJECTED - 반려 - 신고 내용 기각(문제 없음)
