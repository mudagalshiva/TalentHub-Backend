package com.talenthub.controller;

import com.talenthub.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public String uploadResume(
            @RequestParam("file")
            MultipartFile file)
            throws Exception {

        return fileUploadService
                .uploadResume(file);
    }
}