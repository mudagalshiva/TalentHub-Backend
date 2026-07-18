package com.talenthub.controller;

import com.talenthub.dto.CandidateDashboardResponse;
import com.talenthub.dto.RecruiterDashboardResponse;
import com.talenthub.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // ==========================
    // Recruiter Dashboard
    // ==========================
    @GetMapping("/recruiter")
    public RecruiterDashboardResponse getRecruiterDashboard() {

        return dashboardService.getRecruiterDashboard();

    }

    // ==========================
    // Candidate Dashboard
    // ==========================
    @GetMapping("/candidate")
    public CandidateDashboardResponse getCandidateDashboard() {

        return dashboardService.getCandidateDashboard();

    }

    // ==========================
    // Admin Dashboard
    // ==========================
    @GetMapping("/admin/total-jobs")
    public long totalJobs() {

        return dashboardService.getTotalJobs();

    }

    @GetMapping("/admin/total-users")
    public long totalUsers() {

        return dashboardService.getTotalUsers();

    }

    @GetMapping("/admin/total-applications")
    public long totalApplications() {

        return dashboardService.getTotalApplications();

    }

}