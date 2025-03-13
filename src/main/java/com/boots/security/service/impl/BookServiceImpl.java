package com.boots.security.service.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boots.security.dto.request.BookDtoRequest;
import com.boots.security.entity.BookEntity;
import com.boots.security.entity.UserEntity;
import com.boots.security.exeption.BookNotFoundException;
import com.boots.security.repository.BookRepository;
import com.boots.security.repository.UserRepository;
import com.boots.security.service.BookService;
import com.boots.security.util.mappers.BookMapper;


@Service
public class BookServiceImpl implements BookService {

    private final MessageSource messageSource;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ImageStorageServiceImpl imageStorageServiceImpl;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(
        BookRepository bookRepository,
        ImageStorageServiceImpl imageStorageServiceImpl,
        BookMapper bookMapper,
        UserRepository userRepository,
        MessageSource messageSource
    ) {
        this.messageSource =  messageSource;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.imageStorageServiceImpl = imageStorageServiceImpl;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookEntity saveBook(BookDtoRequest bookDtoRequest, UserDetails userDetails) {
        try {
            UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            BookEntity bookEntity = bookMapper.toEntity(bookDtoRequest);
            bookEntity.setAuthor(userEntity);
            return bookRepository.save(bookEntity);
        } catch(Exception e) {
            throw new RuntimeException("Failed to save book.", e);
        }
    }

    @Override
    public BookEntity addImageToBook(Long id, MultipartFile imageFile, Locale locale) {

        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(
         () -> new BookNotFoundException(
            messageSource.getMessage(
                "spring.book.not_found",
                new Object[0],
                locale
            )
         )
        );
        String imageUrl = imageStorageServiceImpl.uploadImage(imageFile);
        bookEntity.setImageUrl(imageUrl);
        return bookRepository.save(bookEntity);
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }


}
