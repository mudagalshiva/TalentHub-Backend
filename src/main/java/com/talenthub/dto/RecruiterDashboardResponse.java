package com.talenthub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruiterDashboardResponse {

    private long totalJobs;
    private long totalApplications;
    private long shortlisted;
    private long selected;
    private long rejected;
}