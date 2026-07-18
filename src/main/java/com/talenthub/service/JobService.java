package com.talenthub.service;

import com.talenthub.dto.JobRequest;
import com.talenthub.entity.Job;
import com.talenthub.entity.User;
import com.talenthub.repository.JobRepository;
import com.talenthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    // Get logged-in recruiter
    private User getLoggedInRecruiter() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Recruiter not found"));
    }

    // Create Job
    public Job createJob(JobRequest request) {

        User recruiter = getLoggedInRecruiter();

        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setCompanyName(request.getCompanyName());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setDescription(request.getDescription());
        job.setSkillsRequired(request.getSkillsRequired());
        job.setJobType(request.getJobType());
        job.setPostedDate(LocalDate.now());

        // Save recruiter id
        job.setRecruiterId(recruiter.getId());

        return jobRepository.save(job);
    }

    // Get all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Get job by id
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));
    }

    // Delete job
    public void deleteJob(Long id) {

        if (!jobRepository.existsById(id)) {
            throw new RuntimeException("Job not found");
        }

        jobRepository.deleteById(id);
    }

    // Recruiter's jobs
    public List<Job> getRecruiterJobs() {

        User recruiter = getLoggedInRecruiter();

        return jobRepository.findByRecruiterId(recruiter.getId());
    }

    // Recruiter job count
    public long getRecruiterJobCount() {

        User recruiter = getLoggedInRecruiter();

        return jobRepository.countByRecruiterId(recruiter.getId());
    }

    public Job updateJob(Long id, Job updatedJob) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setTitle(updatedJob.getTitle());
        job.setCompanyName(updatedJob.getCompanyName());
        job.setLocation(updatedJob.getLocation());
        job.setSalary(updatedJob.getSalary());
        job.setDescription(updatedJob.getDescription());
        job.setSkillsRequired(updatedJob.getSkillsRequired());
        job.setJobType(updatedJob.getJobType());

        // If you add the experience field:
        // job.setExperience(updatedJob.getExperience());

        return jobRepository.save(job);
    }
}