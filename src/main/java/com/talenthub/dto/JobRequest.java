package com.talenthub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequest {

    private String title;
    private String companyName;
    private String location;
    private Double salary;
    private String description;
    private String skillsRequired;
    private String jobType;
}