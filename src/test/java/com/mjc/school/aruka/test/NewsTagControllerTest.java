package com.mjc.school.aruka.test;

import com.mjc.school.aruka.controller.NewsTagController;
import com.mjc.school.aruka.dto.NewsTagDto;
import com.mjc.school.aruka.service.NewsTagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NewsTagControllerTest {

    @Mock
    private NewsTagService newsTagService;

    @InjectMocks
    private NewsTagController newsTagController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllNewsTags() {
        NewsTagDto mockDto = new NewsTagDto(1L, 1L);
        when(newsTagService.getAllNewsTags()).thenReturn(List.of(mockDto));

        ResponseEntity<List<NewsTagDto>> response = newsTagController.getAllNewsTags();

        assertEquals(1, response.getBody().size());
        assertEquals(mockDto, response.getBody().get(0));
        verify(newsTagService, times(1)).getAllNewsTags();
    }

    @Test
    void testGetNewsTagsWithPagination() {
        NewsTagDto mockDto = new NewsTagDto(1L, 1L);
        Page<NewsTagDto> mockPage = new PageImpl<>(List.of(mockDto));
        when(newsTagService.getNewsTagsWithPagination(1L, 1L, PageRequest.of(0, 10)))
                .thenReturn(mockPage);

        ResponseEntity<Page<NewsTagDto>> response = newsTagController.getNewsTagsWithPagination(1L, 1L, PageRequest.of(0, 10));

        assertEquals(1, response.getBody().getTotalElements());
        assertEquals(mockDto, response.getBody().getContent().get(0));
        verify(newsTagService, times(1)).getNewsTagsWithPagination(1L, 1L, PageRequest.of(0, 10));
    }

    @Test
    void testCreateNewsTag() {
        NewsTagDto mockDto = new NewsTagDto(1L, 1L);
        when(newsTagService.createNewsTag(mockDto)).thenReturn(mockDto);

        ResponseEntity<NewsTagDto> response = newsTagController.createNewsTag(mockDto);

        assertEquals(mockDto, response.getBody());
        assertEquals(201, response.getStatusCodeValue());
        verify(newsTagService, times(1)).createNewsTag(mockDto);
    }

    @Test
    void testUpdateNewsTag() {
        Long id = 1L;
        NewsTagDto mockDto = new NewsTagDto(1L, 1L);
        when(newsTagService.updateNewsTag(id, mockDto)).thenReturn(mockDto);

        ResponseEntity<NewsTagDto> response = newsTagController.updateNewsTag(id, mockDto);

        assertEquals(mockDto, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(newsTagService, times(1)).updateNewsTag(id, mockDto);
    }

    @Test
    void testDeleteNewsTag() {
        Long id = 1L;
        doNothing().when(newsTagService).deleteNewsTag(id);

        ResponseEntity<Void> response = newsTagController.deleteNewsTag(id);

        assertEquals(204, response.getStatusCodeValue());
        verify(newsTagService, times(1)).deleteNewsTag(id);
    }
}