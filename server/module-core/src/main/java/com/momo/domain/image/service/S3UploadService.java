package com.momo.domain.image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.momo.domain.image.util.ConvertFileUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        File uploadFile = ConvertFileUtil.convert(multipartFile);
        String uploadImageUrl = process(uploadFile, dirName);
        uploadFile.delete();
        return uploadImageUrl;
    }
    public List<String> uploadAll(List<MultipartFile> multipartFiles, String dirName) throws IOException {
        List<String> uploadUrls = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String uploadImageUrl = upload(multipartFile, dirName);
            uploadUrls.add(uploadImageUrl);
        }
        return uploadUrls;
    }
    private String process(File uploadFile, String dirName) {
        String fileName = generateSaveFileName(dirName, uploadFile.getName());
        return putS3(uploadFile, fileName);
    }
    private String generateSaveFileName(String dirName, String fileName) {
        return String.format("%s/%s_%s", dirName, UUID.randomUUID(), fileName);
    }
    private String putS3(File uploadFile, String fileName) {
        s3Client.putObject(new PutObjectRequest(awsBucketName, fileName, uploadFile)
            .withCannedAcl(CannedAccessControlList.PublicRead));
        return s3Client.getUrl(awsBucketName, fileName).toString();
    }
}