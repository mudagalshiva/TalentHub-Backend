package com.talenthub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantName;

    private String applicantEmail;

    private String resumeUrl;

    private String status;

    private LocalDate appliedDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
}