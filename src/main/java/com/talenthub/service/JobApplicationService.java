package com.talenthub.service;

import com.talenthub.dto.ApplyJobRequest;
import com.talenthub.entity.Job;
import com.talenthub.entity.JobApplication;
import com.talenthub.repository.JobApplicationRepository;
import com.talenthub.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobApplicationService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public JobApplication applyJob(
            ApplyJobRequest request) {

        Job job = jobRepository.findById(
                        request.getJobId())
                .orElseThrow(
                        () -> new RuntimeException("Job Not Found"));

        JobApplication application =
                new JobApplication();

        application.setApplicantName(
                request.getApplicantName());

        application.setApplicantEmail(
                request.getApplicantEmail());

        application.setResumeUrl(
                request.getResumeUrl());

        application.setAppliedDate(
                LocalDate.now());

        application.setStatus("APPLIED");

        application.setJob(job);

        return jobApplicationRepository.save(
                application);
    }
    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    public List<JobApplication> getApplicationsByJob(Long jobId) {
        return jobApplicationRepository.findByJobId(jobId);
    }

    public JobApplication updateStatus(Long id, String status) {

        JobApplication application =
                jobApplicationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Application Not Found"));

        application.setStatus(status);

        return jobApplicationRepository.save(application);
    }
}