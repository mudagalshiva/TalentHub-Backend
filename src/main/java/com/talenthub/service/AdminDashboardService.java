package com.talenthub.service;

import com.talenthub.dto.AdminDashboardResponse;
import com.talenthub.repository.JobApplicationRepository;
import com.talenthub.repository.JobRepository;
import com.talenthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public AdminDashboardResponse getAdminDashboard() {

        long totalUsers =
                userRepository.count();

        long totalRecruiters =
                userRepository.countByRole("RECRUITER");

        long totalJobSeekers =
                userRepository.countByRole("JOB_SEEKER");

        long totalJobs =
                jobRepository.count();

        long totalApplications =
                jobApplicationRepository.count();

        return new AdminDashboardResponse(
                totalUsers,
                totalRecruiters,
                totalJobSeekers,
                totalJobs,
                totalApplications
        );
    }
}