package com.boots.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boots.security.dto.BookImageDto;
import com.boots.security.dto.request.BookDtoRequest;
import com.boots.security.entity.BookEntity;
import com.boots.security.entity.UserEntity;
import com.boots.security.service.BookService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookEntity> createBook(
        @RequestBody BookDtoRequest bookDtoRequest,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        System.out.println(bookDtoRequest.getDescription() + " " + bookDtoRequest.getTitle());
        System.out.println(userDetails);
        System.out.println("yes!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!request");
        BookEntity savedBookEntity = bookService.saveBook(bookDtoRequest, userDetails);
        return new ResponseEntity<>(savedBookEntity, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}/add-image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<BookEntity> addImageToBook(
        @PathVariable Long id,
        @ModelAttribute BookImageDto bookImageDto
    ) {
        return new ResponseEntity<>(
            bookService.addImageToBook(id, bookImageDto.getImage()),
            HttpStatus.CREATED
        );
    }


    @GetMapping
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        List<BookEntity> allBooks = bookService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }
}
