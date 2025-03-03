package com.boots.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boots.security.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
