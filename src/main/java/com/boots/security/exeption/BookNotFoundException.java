package com.boots.security.exeption;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Book not found!");
    }
}
