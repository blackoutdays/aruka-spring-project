package com.mjc.school.aruka.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentDto {
    private Long id;

    @NotBlank(message = "Comment content is required")
    @Size(min = 3, max = 255, message = "Content must be between 3 and 255 characters")
    private String content;

    @NotNull(message = "News ID is required")
    private Long newsId;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }
}