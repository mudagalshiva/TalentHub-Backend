package com.talenthub.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${app.base-url}")
    private String baseUrl;

    public String uploadResume(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new RuntimeException("Please select a resume.");
        }

        String originalFileName = file.getOriginalFilename();

        String fileName = System.currentTimeMillis() + "_" + originalFileName;

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        return baseUrl + "/uploads/" + fileName;
    }
}