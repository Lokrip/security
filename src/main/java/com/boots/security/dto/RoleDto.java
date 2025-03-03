package com.boots.security.dto;


public class RoleDto {
    private String title;

    public RoleDto() {
    }
    public RoleDto(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
