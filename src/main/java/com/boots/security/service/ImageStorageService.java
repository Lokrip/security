package com.boots.security.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    String uploadImage(MultipartFile file);
    String generateFileName(MultipartFile file);
}
