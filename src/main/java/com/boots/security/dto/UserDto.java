package com.boots.security.dto;

import java.util.Set;

import com.boots.security.entity.RoleEntity;

public class UserDto {
    private String username;
    private String email;
    private Set<RoleEntity> roles;
    public UserDto() {
    }
    public UserDto(String username, String email, Set<RoleEntity> roles) {
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<RoleEntity> getRoles() {
        return roles;
    }
    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
    @Override
    public String toString() {
        return "UserDto [username=" + username + ", email=" + email + ", roles=" + roles + "]";
    }
}
