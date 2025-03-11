package com.boots.security.util.mappers.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boots.security.dto.RoleDto;
import com.boots.security.dto.UserDto;
import com.boots.security.entity.RoleEntity;
import com.boots.security.entity.UserEntity;
import com.boots.security.util.mappers.RoleMapper;
import com.boots.security.util.mappers.UserMapper;

@Component
public class UserMapperImpl implements UserMapper {

    private RoleMapper roleMapper;

    @Autowired
    public UserMapperImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDto toDTO(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        Set<RoleDto> rdtos = entity.getRoles()
            .stream()
            .map(roleMapper::toDTO)
            .collect(Collectors.toSet());
        dto.setRoles(rdtos);
        return dto;
    }

    @Override
    public UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        Set<RoleEntity> reEntities = dto.getRoles()
            .stream()
            .map(roleMapper::toEntity)
            .collect(Collectors.toSet());
        entity.setRoles(reEntities);
        return entity;
    }

    @Override
    public List<UserDto> toDTO(List<UserEntity> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserEntity> toEntity(List<UserDto> dtos) {
        // TODO Auto-generated method stub
        return null;
    }
}
