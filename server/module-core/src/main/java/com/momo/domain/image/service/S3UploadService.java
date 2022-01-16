package com.momo.domain.image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.net.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class S3UploadService implements ImageUploadService {

    private final AmazonS3Client s3Client;

    private final String awsBucketName;

    public S3UploadService(AmazonS3Client amazonS3Client,
                           @Value("${cloud.aws.s3.bucket}") String awsBucketName) {
        this.s3Client = amazonS3Client;
        this.awsBucketName = awsBucketName;
    }

    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        String fileName = generateSaveFileName(dirName, multipartFile.getName());
        ObjectMetadata metadata = generateObjectMeta(multipartFile.getSize());

        s3Client.putObject(new PutObjectRequest(awsBucketName, fileName, multipartFile.getInputStream(), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return s3Client.getUrl(awsBucketName, fileName).toString();
    }

    public List<String> uploadAll(List<MultipartFile> multipartFiles, String dirName) throws IOException {
        List<String> uploadUrls = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String uploadImageUrl = upload(multipartFile, dirName);
            uploadUrls.add(uploadImageUrl);
        }
        return uploadUrls;
    }

    private String generateSaveFileName(String dirName, String fileName) {
        return String.format("%s/%s_%s", dirName, UUID.randomUUID(), fileName);
    }

    private ObjectMetadata generateObjectMeta(long fileSize) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(String.valueOf(MediaType.ANY_IMAGE_TYPE));
        metadata.setContentLength(fileSize);

        return metadata;
    }
}