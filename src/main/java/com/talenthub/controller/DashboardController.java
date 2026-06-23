package com.talenthub.controller;

import com.talenthub.dto.RecruiterDashboardResponse;
import com.talenthub.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/recruiter")
    public RecruiterDashboardResponse
    getRecruiterDashboard() {

        return dashboardService
                .getDashboardStats();
    }
}