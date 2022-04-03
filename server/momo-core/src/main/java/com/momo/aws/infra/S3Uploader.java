package com.momo.aws.infra;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class S3Uploader {

    private final AmazonS3Client s3Client;

    private final String bucketName;

    public S3Uploader(AmazonS3Client amazonS3Client, @Value("${cloud.aws.s3.bucket}") String bucketName) {
        this.s3Client = amazonS3Client;
        this.bucketName = bucketName;
    }

    public String save(String fileName, InputStream inputStream, ObjectMetadata metadata) {
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata)
            .withCannedAcl(CannedAccessControlList.PublicRead));
        return s3Client.getUrl(bucketName, fileName).toString();
    }

}
