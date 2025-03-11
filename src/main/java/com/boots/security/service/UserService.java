package com.boots.security.service;

import java.util.List;

import com.boots.security.dto.UserDto;
import com.boots.security.entity.UserEntity;
import com.boots.security.exeption.UserNotCreatedException;

public interface UserService {
    UserEntity findUserById(Long userId);

    List<UserEntity> allUsers();

    boolean saveUser(UserEntity user);

    UserDto createUser(UserDto userDto);

    boolean deleteUser(Long userId);

    List<UserEntity> usergtList(Long idMin);
}
