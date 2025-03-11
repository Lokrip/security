package com.boots.security.dto;

import org.springframework.web.multipart.MultipartFile;

public class BookImageDto {
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
