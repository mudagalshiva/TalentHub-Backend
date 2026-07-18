
package com.talenthub.service;

import com.talenthub.dto.AIResponse;
import com.talenthub.entity.Job;
import com.talenthub.repository.JobRepository;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AIService {

    private final JobRepository jobRepository;


    public AIService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public AIResponse analyzeResume(InputStream inputStream) throws Exception {

        PDDocument document = Loader.loadPDF(inputStream.readAllBytes());

        PDFTextStripper stripper = new PDFTextStripper();

        String resumeText = stripper.getText(document);

        document.close();

        List<String> masterSkills = Arrays.asList(
                "Java","Spring Boot","Hibernate","MySQL","SQL",
                "HTML","CSS","JavaScript","React","JWT","Git",
                "Docker","AWS","Microservices","REST API","Maven"
        );

        Set<String> detectedSkills = new LinkedHashSet<>();

        for(String skill : masterSkills){
            if(resumeText.toLowerCase().contains(skill.toLowerCase())){
                detectedSkills.add(skill);
            }
        }

        List<String> missingSkills = new ArrayList<>();

        for(String skill : Arrays.asList("Docker","AWS","Microservices")){
            if(!detectedSkills.contains(skill)){
                missingSkills.add(skill);
            }
        }

        int resumeScore = Math.min(100,55 + detectedSkills.size()*5);

        List<Map<String,Object>> recommendedJobs = new ArrayList<>();

        for(Job job : jobRepository.findAll()){

            int match = 40;

            if(job.getSkillsRequired()!=null){

                String req = job.getSkillsRequired().toLowerCase();

                for(String skill : detectedSkills){
                    if(req.contains(skill.toLowerCase())){
                        match += 8;
                    }
                }
            }

            Map<String,Object> map = new HashMap<>();
            map.put("id",job.getId());
            map.put("title",job.getTitle());
            map.put("company",job.getCompanyName());
            map.put("match",Math.min(match,100));

            recommendedJobs.add(map);
        }

        recommendedJobs = recommendedJobs.stream()
                .sorted((a,b)->((Integer)b.get("match")).compareTo((Integer)a.get("match")))
                .collect(Collectors.toList());

        List<String> interviewQuestions = new ArrayList<>();

        if(detectedSkills.contains("Java"))
            interviewQuestions.add("Explain JVM, JRE and JDK.");

        if(detectedSkills.contains("Spring Boot"))
            interviewQuestions.add("What are Spring Boot Starters?");

        if(detectedSkills.contains("Hibernate"))
            interviewQuestions.add("Explain Lazy Loading in Hibernate.");

        interviewQuestions.add("Difference between HashMap and Hashtable?");
        interviewQuestions.add("Explain JWT Authentication.");

        List<String> suggestions = new ArrayList<>();

        if(!detectedSkills.contains("Docker"))
            suggestions.add("Learn Docker.");

        if(!detectedSkills.contains("AWS"))
            suggestions.add("Learn AWS.");

        suggestions.add("Add quantified project achievements.");
        suggestions.add("Keep resume limited to one page.");
        suggestions.add("Mention GitHub and LinkedIn profile.");

        return new AIResponse(
                resumeScore,
                new ArrayList<>(detectedSkills),
                missingSkills,
                recommendedJobs,
                interviewQuestions,
                suggestions
        );
    }
}
