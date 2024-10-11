package iitu.edu.kz.aruka.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewsDto {

    private Long id;
    private String title;
    private String content;
    private Long authorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "Title is required")
    @Size(min = 2, max = 30, message = "Title must be between 2 and 30 characters")
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
}