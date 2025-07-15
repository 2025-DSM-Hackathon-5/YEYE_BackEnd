package org.example.yeye_backend.global.thirdparty.S3;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class S3Properties {

    @Value("${cloud.aws.bucket-name}")
    private String bucketName;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("cloud.aws.credentials.secret-key")
    private String secretKey;
}
