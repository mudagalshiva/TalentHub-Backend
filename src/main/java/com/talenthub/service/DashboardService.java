package com.talenthub.service;

import com.talenthub.dto.CandidateDashboardResponse;
import com.talenthub.dto.RecruiterDashboardResponse;
import com.talenthub.entity.Job;
import com.talenthub.entity.JobApplication;
import com.talenthub.repository.JobApplicationRepository;
import com.talenthub.repository.JobRepository;
import com.talenthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    // ==========================
    // Recruiter Dashboard
    // ==========================

    public RecruiterDashboardResponse getRecruiterDashboard() {

        long totalJobs = jobRepository.count();

        long totalApplications = jobApplicationRepository.count();

        long shortlisted =
                jobApplicationRepository.countByStatus("SHORTLISTED");

        long selected =
                jobApplicationRepository.countByStatus("SELECTED");

        long rejected =
                jobApplicationRepository.countByStatus("REJECTED");

        List<Job> jobs =
                jobRepository.findAll();

        List<JobApplication> applications =
                jobApplicationRepository.findAll();

        return new RecruiterDashboardResponse(

                totalJobs,

                totalApplications,

                shortlisted,

                selected,

                rejected,

                jobs,

                applications

        );

    }

    // ==========================
    // Candidate Dashboard
    // ==========================

    public CandidateDashboardResponse getCandidateDashboard() {

        long totalApplications = jobApplicationRepository.count();

        long inProgress =
                jobApplicationRepository.countByStatus("IN_PROGRESS");

        long shortlisted =
                jobApplicationRepository.countByStatus("SHORTLISTED");

        long savedJobs = 0;

        return new CandidateDashboardResponse(
                totalApplications,
                inProgress,
                shortlisted,
                savedJobs
        );
    }

    // ==========================
    // Applications
    // ==========================

    public List<JobApplication> getAllApplications() {

        return jobApplicationRepository.findAll();

    }

    // ==========================
    // Jobs
    // ==========================

    public List<Job> getAllJobs() {

        return jobRepository.findAll();

    }

    // ==========================
    // Admin Counts
    // ==========================

    public long getTotalJobs() {

        return jobRepository.count();

    }

    public long getTotalApplications() {

        return jobApplicationRepository.count();

    }

    public long getTotalUsers() {

        return userRepository.count();

    }

}