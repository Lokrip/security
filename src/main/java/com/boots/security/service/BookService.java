package com.boots.security.service;

import java.util.List;
import java.util.Locale;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.boots.security.dto.BookImageDto;
import com.boots.security.dto.request.BookDtoRequest;
import com.boots.security.entity.BookEntity;

public interface BookService {
    BookEntity saveBook(BookDtoRequest bookDtoRequest, UserDetails userDetails);
    BookEntity addImageToBook(Long id, MultipartFile imageFile, Locale locale);
    List<BookEntity> getAllBooks();
}
