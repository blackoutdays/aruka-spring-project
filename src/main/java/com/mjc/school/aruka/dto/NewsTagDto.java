package com.mjc.school.aruka.dto;

import javax.validation.constraints.NotNull;

public class NewsTagDto {

    private Long id;

    @NotNull(message = "News ID is required")
    private Long newsId;

    @NotNull(message = "Tag ID is required")
    private Long tagId;

    public NewsTagDto() {}

    public NewsTagDto(Long newsId, Long tagId) {
        this.newsId = newsId;
        this.tagId = tagId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsTagDto that = (NewsTagDto) o;

        if (!newsId.equals(that.newsId)) return false;
        return tagId.equals(that.tagId);
    }

    @Override
    public int hashCode() {
        int result = newsId.hashCode();
        result = 31 * result + tagId.hashCode();
        return result;
    }
}