package org.example.yeye_backend.domain.common.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    void uploadFile(MultipartFile file);
}
