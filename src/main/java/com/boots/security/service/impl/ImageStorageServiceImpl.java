package com.boots.security.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boots.security.service.ImageStorageService;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {
    private final MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String bucketName;

    @Value("${minio.url}")
    private String minioUrl;

    @Autowired
    public ImageStorageServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String uploadImage(MultipartFile file) {
        // Генерируем уникальное имя файла
        String fileName = generateFileName(file);

        try (InputStream is = file.getInputStream()) { // Открываем поток ввода из файла
            // Загружаем файл в MinIO
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName) // Указываем bucket (контейнер) для хранения файла
                            .object(fileName) // Имя файла в MinIO
                            .stream(is, file.getSize(), -1) // Передаём поток данных с размером файла
                            .contentType(file.getContentType()) // Указываем MIME-тип файла
                            .build()
            );

            // Возвращаем URL загруженного файла
            return minioUrl + "/" + bucketName + "/" + fileName;
        } catch (Exception e) {
            // В случае ошибки выбрасываем исключение
            throw new RuntimeException("Failed to store image file.", e);
        }
    }

    @Override
    public String generateFileName(MultipartFile file) {
        return new Date().getTime() + "-" + Objects
            .requireNonNull(file.getOriginalFilename())
            .replace(" ", "_");
    }
}
