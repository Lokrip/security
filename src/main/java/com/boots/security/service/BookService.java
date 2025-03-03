package com.boots.security.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.boots.security.entity.BookEntity;

public interface BookService {
    BookEntity saveBook(BookEntity entity, MultipartFile imageFile);
    List<BookEntity> getAllBooks();
}
