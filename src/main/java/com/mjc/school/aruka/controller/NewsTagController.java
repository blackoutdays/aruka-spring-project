package com.mjc.school.aruka.controller;

import com.mjc.school.aruka.dto.NewsTagDto;
import com.mjc.school.aruka.service.NewsTagService;
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
@RequestMapping("/api/news-tags")
@Validated
public class NewsTagController {

    private final NewsTagService newsTagService;
    private static final Logger logger = LoggerFactory.getLogger(NewsTagController.class);

    public NewsTagController(NewsTagService newsTagService) {
        this.newsTagService = newsTagService;
    }

    @Operation(summary = "Get all news-tag relationships")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched all news-tag relationships successfully")
    })
    @GetMapping
    public ResponseEntity<List<NewsTagDto>> getAllNewsTags() {
        logger.info("Fetching all news-tag relationships");
        return ResponseEntity.ok(newsTagService.getAllNewsTags());
    }

    @Operation(summary = "Get paginated news-tag relationships with filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched paginated news-tag relationships successfully")
    })
    @GetMapping("/paged")
    public ResponseEntity<Page<NewsTagDto>> getNewsTagsWithPagination(
            @RequestParam(required = false) Long newsId,
            @RequestParam(required = false) Long tagId,
            Pageable pageable) {
        logger.info("Fetching paginated news-tag relationships");
        return ResponseEntity.ok(newsTagService.getNewsTagsWithPagination(newsId, tagId, pageable));
    }

    @Operation(summary = "Create a new news-tag relationship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "News-tag relationship created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<NewsTagDto> createNewsTag(@Valid @RequestBody NewsTagDto newsTagDto) {
        logger.info("Creating a new news-tag relationship");
        NewsTagDto createdNewsTag = newsTagService.createNewsTag(newsTagDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNewsTag);
    }

    @Operation(summary = "Update a news-tag relationship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "News-tag relationship updated successfully"),
            @ApiResponse(responseCode = "404", description = "News-tag relationship not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<NewsTagDto> updateNewsTag(
            @PathVariable Long id,
            @Valid @RequestBody NewsTagDto newsTagDto) {
        logger.info("Updating a news-tag relationship");
        NewsTagDto updatedNewsTag = newsTagService.updateNewsTag(id, newsTagDto);
        return ResponseEntity.ok(updatedNewsTag);
    }

    @Operation(summary = "Delete a news-tag relationship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "News-tag relationship deleted successfully"),
            @ApiResponse(responseCode = "404", description = "News-tag relationship not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewsTag(@PathVariable Long id) {
        logger.info("Deleting news-tag relationship with ID: {}", id);
        newsTagService.deleteNewsTag(id);
        return ResponseEntity.noContent().build();
    }
}