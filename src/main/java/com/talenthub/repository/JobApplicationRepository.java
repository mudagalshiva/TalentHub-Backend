package com.talenthub.repository;

import com.talenthub.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    // All applications of a candidate
    List<JobApplication> findByCandidateId(Long candidateId);

    // All applications for a job
    List<JobApplication> findByJobId(Long jobId);

    // Applications by email (optional)
    List<JobApplication> findByApplicantEmail(String applicantEmail);

    // Count applications of a candidate
    long countByCandidateId(Long candidateId);

    // Count applications for a job
    long countByJobId(Long jobId);

    // Count applications by status
    long countByStatus(String status);

    // Candidate + Status
    List<JobApplication> findByCandidateIdAndStatus(
            Long candidateId,
            String status
    );

    // Job + Status
    List<JobApplication> findByJobIdAndStatus(
            Long jobId,
            String status
    );

    // Check if candidate already applied
    boolean existsByCandidateIdAndJobId(
            Long candidateId,
            Long jobId
    );

    // Find application by candidate and job
    JobApplication findByCandidateIdAndJobId(
            Long candidateId,
            Long jobId
    );

}