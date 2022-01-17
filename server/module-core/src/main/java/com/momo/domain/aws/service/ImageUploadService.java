package com.momo.domain.aws.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    String upload(MultipartFile multipartFile, String dirName) throws IOException;
    List<String> uploadAll(List<MultipartFile> multipartFiles, String dirName) throws IOException;
}
