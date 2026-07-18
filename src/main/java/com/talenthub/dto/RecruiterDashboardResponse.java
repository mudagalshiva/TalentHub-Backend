package com.talenthub.dto;

import com.talenthub.entity.Job;
import com.talenthub.entity.JobApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterDashboardResponse {

    private long totalJobs;

    private long totalApplications;

    private long shortlisted;

    private long selected;

    private long rejected;

    private List<Job> jobs;

    private List<JobApplication> applications;

}