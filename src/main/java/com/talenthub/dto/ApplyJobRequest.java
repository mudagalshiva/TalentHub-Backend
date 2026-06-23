package com.talenthub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyJobRequest {

    private Long jobId;

    private String applicantName;

    private String applicantEmail;

    private String resumeUrl;
}