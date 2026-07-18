package com.talenthub.dto;
import lombok.*;
import java.util.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AIResponse{
    private int resumeScore;
    private List<String> skills;
    private List<String> missingSkills;
    private List<Map<String,Object>> recommendedJobs;
    private List<String> interviewQuestions;
    private List<String> suggestions;
}
