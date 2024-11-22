package com.mjc.school.aruka.controller;

import com.mjc.school.aruka.dto.CommentDto;
import com.mjc.school.aruka.service.CommentService;
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
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@Validated
public class CommentController {

    private final CommentService commentService;
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Get all comments for a specific news entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched all comments for the news entry successfully")
    })

    @GetMapping("/news/{newsId}")
    public ResponseEntity<List<CommentDto>> getCommentsByNewsId(@PathVariable Long newsId) {
        logger.info("Fetching all comments for news ID: {}", newsId);
        List<CommentDto> comments = commentService.getCommentsByNewsId(newsId);
        return ResponseEntity.ok(comments);
    }


    @Operation(summary = "Get comments with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched comments with pagination successfully")
    })
    @GetMapping
    public ResponseEntity<Page<CommentDto>> getCommentsWithPagination(Pageable pageable) {
        logger.info("Fetching comments with pagination");
        Page<CommentDto> pagedComments = commentService.getCommentsWithPagination(pageable);
        return ResponseEntity.ok(pagedComments);
    }

    @Operation(summary = "Update a comment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable Long id,
            @Valid @RequestBody CommentDto commentDto) {
        logger.info("Updating comment with ID: {}", id);
        CommentDto updatedComment = commentService.updateComment(id, commentDto);
        return ResponseEntity.ok(updatedComment);
    }

    @Operation(summary = "Get a comment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        logger.info("Fetching comment with ID: {}", id);
        CommentDto comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/sorted")
    public ResponseEntity<Page<CommentDto>> getSortedComments(
            Pageable pageable,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        logger.info("Fetching sorted comments");
        Page<CommentDto> sortedComments = commentService.getSortedComments(pageable, sortBy, direction);
        return ResponseEntity.ok(sortedComments);
    }

    @Operation(summary = "Create a new comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto) {
        logger.info("Creating a new comment: {}", commentDto);
        CommentDto createdComment = commentService.createComment(commentDto);

        // Добавляем URI созданного ресурса
        URI location = URI.create(String.format("/api/comments/%d", createdComment.getId()));
        return ResponseEntity.created(location).body(createdComment);
    }

    @Operation(summary = "Delete a comment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        logger.info("Deleting comment with ID: {}", id);
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}