package com.boots.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boots.security.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
