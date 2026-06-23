package com.talenthub.controller;

import com.talenthub.dto.AdminDashboardResponse;
import com.talenthub.service.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService adminDashboardService;

    @GetMapping("/dashboard")
    public AdminDashboardResponse getDashboard() {

        return adminDashboardService.getAdminDashboard();
    }
}