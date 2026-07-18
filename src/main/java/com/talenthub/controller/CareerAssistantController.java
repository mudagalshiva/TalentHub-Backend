package com.talenthub.controller;
import com.talenthub.dto.AIResponse;
import com.talenthub.service.AIService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins="*")
public class CareerAssistantController{
    private final AIService aiService;
    public CareerAssistantController(AIService aiService){this.aiService=aiService;}
    @PostMapping("/analyze")
    public AIResponse analyze(@RequestParam("file") MultipartFile file)throws Exception{
        return aiService.analyzeResume(file.getInputStream());
    }}