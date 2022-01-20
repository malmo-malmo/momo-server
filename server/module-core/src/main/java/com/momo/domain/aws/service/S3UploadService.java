package com.momo.domain.aws.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.momo.domain.aws.infra.S3Uploader;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3UploadService implements ImageUploadService {

    private final S3Uploader s3Uploader;

    public String upload(MultipartFile multipartFile, String dirName) {
        if (Objects.isNull(multipartFile)) {
            return null;
        }

        String fileName = generateSaveFileName(dirName, multipartFile.getName());
        ObjectMetadata metadata = generateObjectMeta(multipartFile.getContentType(), multipartFile.getSize());
        try {
            return s3Uploader.save(fileName, multipartFile.getInputStream(), metadata);
        } catch (IOException e) {
            throw new CustomException(ErrorCode.IMAGE_FILE_UPLOAD_ERROR);
        }
    }

    public List<String> uploadAll(List<MultipartFile> multipartFiles, String dirName) {
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

    private ObjectMetadata generateObjectMeta(String contentType, long fileSize) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(fileSize);

        return metadata;
    }
}
