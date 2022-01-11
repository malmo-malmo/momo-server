package com.momo.domain.image.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    String upload(MultipartFile multipartFile, String dirName) throws IOException;
}
