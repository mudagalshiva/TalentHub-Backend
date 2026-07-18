package com.talenthub.service;

import com.talenthub.dto.ApplyJobRequest;
import com.talenthub.entity.Job;
import com.talenthub.entity.JobApplication;
import com.talenthub.entity.User;
import com.talenthub.repository.JobApplicationRepository;
import com.talenthub.repository.JobRepository;
import com.talenthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobApplicationService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    private User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public JobApplication applyJob(
            ApplyJobRequest request) {

        User candidate = getLoggedInUser();

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        boolean alreadyApplied =
                jobApplicationRepository.existsByCandidateIdAndJobId(
                        candidate.getId(),
                        job.getId());

        if (alreadyApplied) {
            throw new RuntimeException(
                    "You have already applied for this job.");
        }

        JobApplication application = new JobApplication();

        application.setJob(job);

        application.setCandidateId(candidate.getId());

        application.setApplicantName(candidate.getName());

        application.setApplicantEmail(candidate.getEmail());

        application.setCoverLetter(request.getCoverLetter());

        application.setResumeUrl(request.getResumeUrl());

        application.setAppliedDate(LocalDate.now());

        application.setStatus("APPLIED");

        return jobApplicationRepository.save(application);

    }

    public List<JobApplication> getAllApplications() {

        return jobApplicationRepository.findAll();

    }

    public List<JobApplication> getCandidateApplications() {

        User candidate = getLoggedInUser();

        return jobApplicationRepository.findByCandidateId(
                candidate.getId());

    }

    public List<JobApplication> getApplicationsByJob(
            Long jobId) {

        return jobApplicationRepository.findByJobId(jobId);

    }
    public JobApplication getApplicationById(
            Long applicationId) {

        return jobApplicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new RuntimeException("Application not found"));

    }

    public JobApplication updateStatus(
            Long applicationId,
            String status) {

        JobApplication application =
                jobApplicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setStatus(status.toUpperCase());

        return jobApplicationRepository.save(application);

    }

    public void deleteApplication(
            Long applicationId) {

        JobApplication application =
                jobApplicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        jobApplicationRepository.delete(application);

    }

    public long getTotalApplications() {

        return jobApplicationRepository.count();

    }

    public long getCandidateApplicationCount(Long candidateId) {

        User candidate = getLoggedInUser();

        return jobApplicationRepository.countByCandidateId(
                candidate.getId());

    }

    public long getJobApplicationCount(
            Long jobId) {

        return jobApplicationRepository.countByJobId(jobId);

    }

    public long getAppliedCount() {

        return jobApplicationRepository.countByStatus("APPLIED");

    }

    public long getInterviewCount() {

        return jobApplicationRepository.countByStatus("INTERVIEW");

    }

    public long getSelectedCount() {

        return jobApplicationRepository.countByStatus("SELECTED");

    }

    public long getRejectedCount() {

        return jobApplicationRepository.countByStatus("REJECTED");

    }

    public List<JobApplication> getApplicationsByCandidate(Long candidateId) {

        return jobApplicationRepository.findByCandidateId(candidateId);

    }
}