package com.boots.security.util.mappers.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.boots.security.dto.RoleDto;
import com.boots.security.entity.RoleEntity;
import com.boots.security.util.mappers.RoleMapper;

@Component
public class RoleMapperImpl implements RoleMapper {
    @Override
    public RoleDto toDTO(RoleEntity entity) {
        RoleDto dto = new RoleDto();
        dto.setTitle(entity.getTitle());
        return dto;
    }

    @Override
    public List<RoleDto> toDTO(List<RoleEntity> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RoleEntity> toEntity(List<RoleDto> dtos) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RoleEntity toEntity(RoleDto dto) {
        RoleEntity entity = new RoleEntity();
        entity.setTitle(dto.getTitle());
        return entity;
    }
}
