package com.momo.aws.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {

    String upload(MultipartFile multipartFile, String dirName);

    List<String> uploadAll(List<MultipartFile> multipartFiles, String dirName);
}
