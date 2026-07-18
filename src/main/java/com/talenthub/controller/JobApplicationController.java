package com.talenthub.controller;

import com.talenthub.dto.ApplyJobRequest;
import com.talenthub.entity.JobApplication;
import com.talenthub.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    // Apply for a job
    @PostMapping("/apply")
    public JobApplication applyJob(
            @RequestBody ApplyJobRequest request) {

        return jobApplicationService.applyJob(request);
    }

    // Get all applications (Admin)
    @GetMapping
    public List<JobApplication> getAllApplications() {

        System.out.println("===== APPLICATION API HIT =====");

        return jobApplicationService.getAllApplications();
    }

    // Get all applications of a candidate
    @GetMapping("/candidate/{candidateId}")
    public List<JobApplication> getApplicationsByCandidate(
            @PathVariable Long candidateId) {

        return jobApplicationService.getApplicationsByCandidate(candidateId);
    }

    // Get all applications for a job
    @GetMapping("/job/{jobId}")
    public List<JobApplication> getApplicationsByJob(
            @PathVariable Long jobId) {

        return jobApplicationService.getApplicationsByJob(jobId);
    }

    // Get single application
    @GetMapping("/{id}")
    public JobApplication getApplication(
            @PathVariable Long id) {

        return jobApplicationService.getApplicationById(id);
    }

    // Update application status
    @PutMapping("/{id}/status")
    public JobApplication updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return jobApplicationService.updateStatus(id, status);
    }

    // Delete application
    @DeleteMapping("/{id}")
    public String deleteApplication(
            @PathVariable Long id) {

        jobApplicationService.deleteApplication(id);

        return "Application Deleted Successfully";
    }

    // Dashboard APIs

    @GetMapping("/count")
    public long totalApplications() {

        return jobApplicationService.getTotalApplications();
    }

    @GetMapping("/candidate/{candidateId}/count")
    public long candidateCount(
            @PathVariable Long candidateId) {

        return jobApplicationService.getCandidateApplicationCount(candidateId);
    }

    @GetMapping("/job/{jobId}/count")
    public long jobCount(
            @PathVariable Long jobId) {

        return jobApplicationService.getJobApplicationCount(jobId);
    }

    @GetMapping("/count/applied")
    public long appliedCount() {

        return jobApplicationService.getAppliedCount();
    }

    @GetMapping("/count/interview")
    public long interviewCount() {

        return jobApplicationService.getInterviewCount();
    }

    @GetMapping("/count/selected")
    public long selectedCount() {

        return jobApplicationService.getSelectedCount();
    }

    @GetMapping("/count/rejected")
    public long rejectedCount() {

        return jobApplicationService.getRejectedCount();
    }

}