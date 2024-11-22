package com.mjc.school.aruka.controller;

import com.mjc.school.aruka.dto.TagDto;
import com.mjc.school.aruka.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
@Validated
public class TagController {

    private final TagService tagService;
    private static final Logger logger = LoggerFactory.getLogger(TagController.class); // Добавлено определение logger

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @Operation(summary = "Search tags by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched filtered tags successfully")
    })
    @GetMapping("/search")
    public ResponseEntity<Page<TagDto>> searchTags(
            @RequestParam(required = false) String name,
            Pageable pageable) {
        logger.info("Searching tags with name: {}", name);
        return ResponseEntity.ok(tagService.searchTags(name, pageable));
    }

    @Operation(summary = "Get all tags with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched all tags with pagination successfully")
    })
    @GetMapping("/paged")
    public ResponseEntity<Page<TagDto>> getTagsWithPagination(Pageable pageable) {
        logger.info("Fetching tags with pagination");
        return ResponseEntity.ok(tagService.getTagsWithPagination(pageable));
    }

    @Operation(summary = "Get all tags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched all tags successfully")
    })
    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        logger.info("Fetching all tags");
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @Operation(summary = "Get tag by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Tag not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable Long id) {
        logger.info("Fetching tag with ID: {}", id);
        return ResponseEntity.ok(tagService.getTagById(id));
    }

    @Operation(summary = "Create a new tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tag created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<TagDto> createTag(@Valid @RequestBody TagDto tagDto) {
        logger.info("Creating new tag: {}", tagDto);
        return ResponseEntity.ok(tagService.createTag(tagDto));
    }

    @Operation(summary = "Update a tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag updated successfully"),
            @ApiResponse(responseCode = "404", description = "Tag not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TagDto> updateTag(@PathVariable Long id, @Valid @RequestBody TagDto tagDto) {
        logger.info("Updating tag with ID: {}", id);
        return ResponseEntity.ok(tagService.updateTag(id, tagDto));
    }

    @Operation(summary = "Delete a tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tag deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Tag not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        logger.info("Deleting tag with ID: {}", id);
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}