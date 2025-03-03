package com.boots.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boots.security.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>  {
    Optional<RoleEntity> findByTitle(String title);
}
