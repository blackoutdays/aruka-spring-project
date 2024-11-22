package com.mjc.school.aruka.test;

import com.mjc.school.aruka.controller.CommentController;
import com.mjc.school.aruka.dto.CommentDto;
import com.mjc.school.aruka.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommentControllerIT {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommentsByNewsId() {
        Long newsId = 1L;
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("Test Comment");

        when(commentService.getCommentsByNewsId(newsId)).thenReturn(List.of(commentDto));

        ResponseEntity<List<CommentDto>> response = commentController.getCommentsByNewsId(newsId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Comment", response.getBody().get(0).getContent());

        verify(commentService, times(1)).getCommentsByNewsId(newsId);
    }

    @Test
    void testCreateComment() {
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("New Comment");

        when(commentService.createComment(commentDto)).thenReturn(commentDto);

        ResponseEntity<CommentDto> response = commentController.createComment(commentDto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("New Comment", response.getBody().getContent());

        verify(commentService, times(1)).createComment(commentDto);
    }

    @Test
    void testUpdateComment() {
        Long commentId = 1L;
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("Updated Comment");

        when(commentService.updateComment(commentId, commentDto)).thenReturn(commentDto);

        ResponseEntity<CommentDto> response = commentController.updateComment(commentId, commentDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated Comment", response.getBody().getContent());

        verify(commentService, times(1)).updateComment(commentId, commentDto);
    }

    @Test
    void testDeleteComment() {
        Long commentId = 1L;
        doNothing().when(commentService).deleteComment(commentId);

        ResponseEntity<Void> response = commentController.deleteComment(commentId);

        assertEquals(204, response.getStatusCodeValue());
        verify(commentService, times(1)).deleteComment(commentId);
    }

    @Test
    void testGetCommentsWithPagination() {
        Pageable pageable = mock(Pageable.class);
        Page<CommentDto> mockPage = mock(Page.class);

        when(commentService.getCommentsWithPagination(pageable)).thenReturn(mockPage);

        ResponseEntity<Page<CommentDto>> response = commentController.getCommentsWithPagination(pageable);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockPage, response.getBody());

        verify(commentService, times(1)).getCommentsWithPagination(pageable);
    }
}