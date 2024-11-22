package com.mjc.school.aruka.test;

import com.mjc.school.aruka.controller.NewsController;
import com.mjc.school.aruka.dto.NewsDto;
import com.mjc.school.aruka.service.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NewsControllerTest {

    @Mock
    private NewsService newsService;

    @InjectMocks
    private NewsController newsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllNews() {
        NewsDto mockNews = new NewsDto(1L, "Test Title", "Test Content", 1L, Collections.emptyList());
        when(newsService.getAllNews()).thenReturn(List.of(mockNews));

        ResponseEntity<List<NewsDto>> response = newsController.getAllNews();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(newsService, times(1)).getAllNews();
    }

    @Test
    void testCreateNews() {
        NewsDto mockNews = new NewsDto(1L, "Test Title", "Test Content", 1L, Collections.emptyList());
        NewsDto inputNews = new NewsDto(null, "New Title", "New Content", 1L, Collections.emptyList());
        NewsDto savedNews = new NewsDto(1L, "New Title", "New Content", 1L, Collections.emptyList());
        when(newsService.createNews(inputNews)).thenReturn(savedNews);

        ResponseEntity<NewsDto> response = newsController.createNews(inputNews);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("New Title", response.getBody().getTitle());
        verify(newsService, times(1)).createNews(inputNews);
    }

    @Test
    void testGetNewsById() {
        Long newsId = 1L;
        NewsDto mockNews = new NewsDto(1L, "Test Title", "Test Content", 1L, Collections.emptyList());
        when(newsService.getNewsById(newsId)).thenReturn(mockNews);

        ResponseEntity<NewsDto> response = newsController.getNewsById(newsId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Title", response.getBody().getTitle());
        verify(newsService, times(1)).getNewsById(newsId);
    }

    @Test
    void testGetPaginatedNews() {
        Page<NewsDto> mockPage = new PageImpl<>(Collections.emptyList());
        when(newsService.getPaginatedNews(0, 10, "createdDate", "desc")).thenReturn(mockPage);

        ResponseEntity<Page<NewsDto>> response = newsController.getPaginatedNews(0, 10, "createdDate", "desc");

        assertEquals(200, response.getStatusCodeValue());
        verify(newsService, times(1)).getPaginatedNews(0, 10, "createdDate", "desc");
    }

    @Test
    void testDeleteNews() {
        Long newsId = 1L;
        when(newsService.deleteNews(newsId)).thenReturn(true);

        ResponseEntity<Void> response = newsController.deleteNews(newsId);

        assertEquals(204, response.getStatusCodeValue());
        verify(newsService, times(1)).deleteNews(newsId);
    }
}