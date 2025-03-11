package com.boots.security.dto.request;

public class BookDtoRequest {
    private Long id;
    private String title;
    private String description;
    public BookDtoRequest(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    public BookDtoRequest() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "BookDtoRequest [id=" + id + ", title=" + title + ", description=" + description + "]";
    }


}
