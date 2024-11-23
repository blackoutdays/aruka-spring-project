package com.mjc.school.aruka.controller;

import com.mjc.school.aruka.dto.NewsDto;
import com.mjc.school.aruka.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@Validated
public class NewsController {

    private final NewsService newsService;
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Operation(summary = "Get all news entries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched all news entries successfully")
    })
    @GetMapping
    public ResponseEntity<List<NewsDto>> getAllNews() {
        logger.info("Fetching all news entries");
        List<NewsDto> newsList = newsService.getAllNews();
        return ResponseEntity.ok(newsList);
    }

    @Operation(summary = "Create a new news entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "News created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<NewsDto> createNews(@Valid @RequestBody NewsDto newsDto) {
        logger.info("Creating news entry: {}", newsDto);
        NewsDto createdNews = newsService.createNews(newsDto);
        return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a news entry by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "News entry fetched successfully"),
            @ApiResponse(responseCode = "404", description = "News entry not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable Long id) {
        logger.info("Fetching news entry by ID: {}", id);
        NewsDto news = newsService.getNewsById(id);
        return ResponseEntity.ok(news);
    }

    @Operation(summary = "Get paginated list of news entries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched paginated news entries successfully")
    })
    @GetMapping("/paged")
    public ResponseEntity<Page<NewsDto>> getPaginatedNews(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy,
            @RequestParam(value = "direction", defaultValue = "desc") String direction) {
        logger.info("Fetching paginated news entries");
        Page<NewsDto> newsPage = newsService.getPaginatedNews(page, size, sortBy, direction);
        return ResponseEntity.ok(newsPage);
    }

    @Operation(summary = "Search news with filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched filtered news successfully")
    })
    @GetMapping("/search")
    public ResponseEntity<Page<NewsDto>> searchNews(
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) List<String> tags,
            Pageable pageable) {
        logger.info("Searching news with filters - Author: {}, Title: {}, Content: {}, Tags: {}", authorName, title, content, tags);
        Page<NewsDto> filteredNews = newsService.searchNews(authorName, title, content, tags, pageable);
        return ResponseEntity.ok(filteredNews);
    }

    @Operation(summary = "Update a news entry by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "News updated successfully"),
            @ApiResponse(responseCode = "404", description = "News entry not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<NewsDto> updateNews(@PathVariable Long id, @Valid @RequestBody NewsDto newsDto) {
        logger.info("Updating news entry with ID: {}", id);
        NewsDto updatedNews = newsService.updateNews(id, newsDto);
        return ResponseEntity.ok(updatedNews);
    }

    @Operation(summary = "Delete a news entry by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "News deleted successfully"),
            @ApiResponse(responseCode = "404", description = "News entry not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        logger.info("Deleting news entry with ID: {}", id);
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}