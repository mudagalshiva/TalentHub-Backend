package com.talenthub.repository;

import com.talenthub.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    long count();
    List<Job> findByRecruiterId(Long recruiterId);

    long countByRecruiterId(Long recruiterId);
}