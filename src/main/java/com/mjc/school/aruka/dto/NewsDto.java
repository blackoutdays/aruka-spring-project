package com.mjc.school.aruka.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class NewsDto {

    private Long id;

    @NotNull(message = "Title is required")
    @Size(min = 2, max = 30, message = "Title must be between 2 and 30 characters")
    private String title;

    @NotNull(message = "Content is required")
    @Size(min = 5, max = 255, message = "Content must be between 5 and 255 characters")
    private String content;

    @NotNull(message = "Author ID is required")
    private Long authorId;

    private List<String> tags; // Assuming tags are represented as Strings

    public NewsDto() {}

    public NewsDto(Long id, String title, String content, Long authorId, List<String> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.tags = tags;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}