package com.boots.security.util.mappers.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.boots.security.dto.request.BookDtoRequest;
import com.boots.security.entity.BookEntity;
import com.boots.security.util.mappers.BookMapper;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDtoRequest toDTO(BookEntity entity) {
        BookDtoRequest dto = new BookDtoRequest();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    public BookEntity toEntity(BookDtoRequest dto) {
        BookEntity entity = new BookEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    @Override
    public List<BookDtoRequest> toDTO(List<BookEntity> entities) {
        return null;
    }

    @Override
    public List<BookEntity> toEntity(List<BookDtoRequest> dtos) {
        return null;
    }

}
