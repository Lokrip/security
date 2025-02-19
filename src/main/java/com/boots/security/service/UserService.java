package com.boots.security.service;

import java.util.List;

import com.boots.security.dto.UserDto;
import com.boots.security.entity.UserEntity;

public interface UserService {
    UserEntity findUserById(Long userId);

    List<UserEntity> allUsers();

    boolean saveUser(UserEntity user);

    UserDto createUser(UserEntity user);

    boolean deleteUser(Long userId);

    List<UserEntity> usergtList(Long idMin);
}
