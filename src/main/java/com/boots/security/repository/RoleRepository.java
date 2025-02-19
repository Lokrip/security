package com.boots.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boots.security.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>  {
    
}
