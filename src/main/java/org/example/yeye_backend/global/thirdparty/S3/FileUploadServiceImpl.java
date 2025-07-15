package org.example.yeye_backend.global.thirdparty.S3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.yeye_backend.domain.common.service.FileUploadService;
import org.example.yeye_backend.global.exception.generalExceptions.InternalServerErrorException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
    private final AmazonS3Client amazonS3Client;
    private final S3Properties s3Properties;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            String name = UUID.randomUUID() + "_" + file.getOriginalFilename();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            amazonS3Client.putObject(s3Properties.getBucketName(), name, file.getInputStream(), objectMetadata);

            return amazonS3Client.getResourceUrl(s3Properties.getBucketName(), name);
        } catch (Exception e) {
            log.error("file upload failed. reason: " + e.getMessage());
            throw InternalServerErrorException.EXCEPTION;
        }
    }
}
