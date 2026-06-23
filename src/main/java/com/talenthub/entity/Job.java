package com.talenthub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String companyName;

    private String location;

    private Double salary;

    @Column(length = 2000)
    private String description;

    private String skillsRequired;

    private String jobType;

    private LocalDate postedDate;
}