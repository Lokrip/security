package com.boots.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boots.security.entity.BookEntity;
import com.boots.security.repository.BookRepository;
import com.boots.security.service.BookService;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ImageStorageServiceImpl imageStorageServiceImpl;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ImageStorageServiceImpl imageStorageServiceImpl) {
        this.bookRepository = bookRepository;
        this.imageStorageServiceImpl = imageStorageServiceImpl;
    }

    @Override
    public BookEntity saveBook(BookEntity bookEntity, MultipartFile imageFile) {
        try {
            String imageUrl = imageStorageServiceImpl.uploadImage(imageFile);
            bookEntity.setImageUrl(imageUrl);
            return bookRepository.save(bookEntity);
        } catch(Exception e) {
            throw new RuntimeException("Failed to save book.", e);
        }
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }


}
