package com.example.roomfit.web;

import com.example.roomfit.domain.ReportStatus;
import com.example.roomfit.service.AdminService;
import com.example.roomfit.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ReportService reportService;

    @GetMapping
    public String dashboard(Model model){
        model.addAttribute("stats", adminService.dashboard()); //stats(카운트 4)
        model.addAttribute("reports", reportService.pendingReports()); //reports (대기신고목록)
        return "admin/dashboard";
    }

    //관리자가 신고(처리) 버튼을 클릭하면 호출
    @PostMapping("/reports/{id}")
    public String processReport(
            @PathVariable("id") Long id, //처리할 신고의 reports.id
            @RequestParam("status")ReportStatus status, //ReportStatus enum(processed)
            @RequestParam(name = "adminNote", required = false) String adminNote){ //관리자 메모 - 지금 폼에는 없어서 null
        reportService.process(id, status, adminNote);
        return "redirect:/admin";
    }
    //reportService.process(id, status, adminNote);
    //report 테이블에서 3을 조회
    //status = processed 로 변경 (update)
    //"redirect:/admin"; -> 게시판 pending만 조회 하므로 화면에서 사라짐

}
