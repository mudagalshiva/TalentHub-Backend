package com.talenthub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDashboardResponse {

    private long totalUsers;
    private long totalRecruiters;
    private long totalJobSeekers;
    private long totalJobs;
    private long totalApplications;
}