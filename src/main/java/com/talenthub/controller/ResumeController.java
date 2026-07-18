package com.talenthub.controller;

import com.talenthub.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadResume(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        String resumeUrl =
                fileUploadService.uploadResume(file);

        Map<String, String> response =
                new HashMap<>();

        response.put("resumeUrl", resumeUrl);

        return ResponseEntity.ok(response);
    }
}