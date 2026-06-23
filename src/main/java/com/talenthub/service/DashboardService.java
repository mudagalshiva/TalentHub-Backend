package com.talenthub.service;

import com.talenthub.dto.RecruiterDashboardResponse;
import com.talenthub.repository.JobApplicationRepository;
import com.talenthub.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public RecruiterDashboardResponse getDashboardStats() {

        long totalJobs =
                jobRepository.count();

        long totalApplications =
                jobApplicationRepository.count();

        long shortlisted =
                jobApplicationRepository
                        .countByStatus("SHORTLISTED");

        long selected =
                jobApplicationRepository
                        .countByStatus("SELECTED");

        long rejected =
                jobApplicationRepository
                        .countByStatus("REJECTED");

        return new RecruiterDashboardResponse(
                totalJobs,
                totalApplications,
                shortlisted,
                selected,
                rejected
        );
    }
}