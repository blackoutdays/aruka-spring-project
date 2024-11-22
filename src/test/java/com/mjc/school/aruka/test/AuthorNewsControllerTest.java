package com.mjc.school.aruka.test;

import com.mjc.school.aruka.controller.AuthorNewsController;
import com.mjc.school.aruka.dto.AuthorNewsDto;
import com.mjc.school.aruka.service.AuthorNewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthorNewsControllerTest {

    @Mock
    private AuthorNewsService authorNewsService;

    @InjectMocks
    private AuthorNewsController authorNewsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAuthorNews() {
        AuthorNewsDto mockAuthor = new AuthorNewsDto();
        mockAuthor.setId(1L);
        mockAuthor.setFirstName("John");
        mockAuthor.setLastName("Doe");
        mockAuthor.setEmail("john.doe@example.com");

        when(authorNewsService.getAllAuthorNews()).thenReturn(List.of(mockAuthor));

        ResponseEntity<List<AuthorNewsDto>> response = authorNewsController.getAllAuthorNews();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("John", response.getBody().get(0).getFirstName());
        verify(authorNewsService, times(1)).getAllAuthorNews();
    }

    @Test
    void testCreateAuthorNews() {
        AuthorNewsDto inputAuthor = new AuthorNewsDto();
        inputAuthor.setFirstName("Jane");
        inputAuthor.setLastName("Smith");
        inputAuthor.setEmail("jane.smith@example.com");

        AuthorNewsDto savedAuthor = new AuthorNewsDto();
        savedAuthor.setId(1L);
        savedAuthor.setFirstName("Jane");
        savedAuthor.setLastName("Smith");
        savedAuthor.setEmail("jane.smith@example.com");

        when(authorNewsService.createAuthorNews(inputAuthor)).thenReturn(savedAuthor);

        ResponseEntity<AuthorNewsDto> response = authorNewsController.createAuthorNews(inputAuthor);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Jane", response.getBody().getFirstName());
        verify(authorNewsService, times(1)).createAuthorNews(inputAuthor);
    }

    @Test
    void testGetAuthorNewsById() {
        Long authorId = 1L;
        AuthorNewsDto mockAuthor = new AuthorNewsDto();
        mockAuthor.setId(authorId);
        mockAuthor.setFirstName("John");
        mockAuthor.setLastName("Doe");
        mockAuthor.setEmail("john.doe@example.com");

        when(authorNewsService.getAuthorNewsById(authorId)).thenReturn(mockAuthor);

        ResponseEntity<AuthorNewsDto> response = authorNewsController.getAuthorNewsById(authorId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John", response.getBody().getFirstName());
        verify(authorNewsService, times(1)).getAuthorNewsById(authorId);
    }

    @Test
    void testUpdateAuthorNews() {
        Long authorId = 1L;
        AuthorNewsDto updatedAuthor = new AuthorNewsDto();
        updatedAuthor.setFirstName("John");
        updatedAuthor.setLastName("Doe Updated");
        updatedAuthor.setEmail("john.doe.updated@example.com");

        AuthorNewsDto returnedAuthor = new AuthorNewsDto();
        returnedAuthor.setId(authorId);
        returnedAuthor.setFirstName("John");
        returnedAuthor.setLastName("Doe Updated");
        returnedAuthor.setEmail("john.doe.updated@example.com");

        when(authorNewsService.updateAuthorNews(authorId, updatedAuthor)).thenReturn(returnedAuthor);

        ResponseEntity<AuthorNewsDto> response = authorNewsController.updateAuthorNews(authorId, updatedAuthor);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Doe Updated", response.getBody().getLastName());
        verify(authorNewsService, times(1)).updateAuthorNews(authorId, updatedAuthor);
    }

    @Test
    void testDeleteAuthorNews() {
        Long authorId = 1L;
        doNothing().when(authorNewsService).deleteAuthorNews(authorId);

        ResponseEntity<Void> response = authorNewsController.deleteAuthorNews(authorId);

        assertEquals(204, response.getStatusCodeValue());
        verify(authorNewsService, times(1)).deleteAuthorNews(authorId);
    }
}