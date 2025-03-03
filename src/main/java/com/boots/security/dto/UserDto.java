package com.boots.security.dto;

import java.util.Set;


public class UserDto {
    private String username;
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    private String email;
    private Set<RoleDto> roles;
    public Set<RoleDto> getRoles() {
        return roles;
    }
    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }
    public UserDto() {
    }
    public UserDto(String username, String email, Set<RoleDto> roles) {
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

    @Override
    public String toString() {
        return "UserDto{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
