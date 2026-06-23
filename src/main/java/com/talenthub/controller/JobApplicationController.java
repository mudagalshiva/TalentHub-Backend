package com.talenthub.controller;

import com.talenthub.dto.ApplyJobRequest;
import com.talenthub.entity.JobApplication;
import com.talenthub.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping("/apply")
    public JobApplication applyJob(
            @RequestBody ApplyJobRequest request) {

        return jobApplicationService.applyJob(request);
    }

    @GetMapping
    public List<JobApplication> getAllApplications() {
        return jobApplicationService.getAllApplications();
    }

    @GetMapping("/job/{jobId}")
    public List<JobApplication> getApplicationsByJob(
            @PathVariable Long jobId) {

        return jobApplicationService.getApplicationsByJob(jobId);
    }

    @PutMapping("/{id}/status")
    public JobApplication updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return jobApplicationService.updateStatus(id, status);
    }
}