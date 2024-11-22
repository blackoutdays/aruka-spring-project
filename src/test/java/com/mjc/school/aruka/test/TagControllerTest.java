package com.mjc.school.aruka.test;

import com.mjc.school.aruka.controller.TagController;
import com.mjc.school.aruka.dto.TagDto;
import com.mjc.school.aruka.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TagControllerTest {

    @Mock
    private TagService tagService;

    @InjectMocks
    private TagController tagController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchTags() {
        Page<TagDto> mockPage = new PageImpl<>(Collections.singletonList(new TagDto(1L, "TestTag")));
        when(tagService.searchTags("TestTag", null)).thenReturn(mockPage);

        ResponseEntity<Page<TagDto>> response = tagController.searchTags("TestTag", null);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getTotalElements());
        verify(tagService, times(1)).searchTags("TestTag", null);
    }

    @Test
    void testGetTagsWithPagination() {
        Page<TagDto> mockPage = new PageImpl<>(Collections.singletonList(new TagDto(1L, "TestTag")));
        when(tagService.getTagsWithPagination(null)).thenReturn(mockPage);

        ResponseEntity<Page<TagDto>> response = tagController.getTagsWithPagination(null);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getTotalElements());
        verify(tagService, times(1)).getTagsWithPagination(null);
    }

    @Test
    void testGetAllTags() {
        List<TagDto> mockTags = List.of(new TagDto(1L, "TestTag1"), new TagDto(2L, "TestTag2"));
        when(tagService.getAllTags()).thenReturn(mockTags);

        ResponseEntity<List<TagDto>> response = tagController.getAllTags();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(tagService, times(1)).getAllTags();
    }

    @Test
    void testGetTagById() {
        Long tagId = 1L;
        TagDto mockTag = new TagDto(1L, "TestTag");
        when(tagService.getTagById(tagId)).thenReturn(mockTag);

        ResponseEntity<TagDto> response = tagController.getTagById(tagId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("TestTag", response.getBody().getName());
        verify(tagService, times(1)).getTagById(tagId);
    }

    @Test
    void testCreateTag() {
        TagDto inputTag = new TagDto(null, "NewTag");
        TagDto savedTag = new TagDto(1L, "NewTag");
        when(tagService.createTag(inputTag)).thenReturn(savedTag);

        ResponseEntity<TagDto> response = tagController.createTag(inputTag);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("NewTag", response.getBody().getName());
        verify(tagService, times(1)).createTag(inputTag);
    }

    @Test
    void testUpdateTag() {
        Long tagId = 1L;
        TagDto updatedTagDto = new TagDto(tagId, "UpdatedTag");
        when(tagService.updateTag(tagId, updatedTagDto)).thenReturn(updatedTagDto);

        ResponseEntity<TagDto> response = tagController.updateTag(tagId, updatedTagDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("UpdatedTag", response.getBody().getName());
        verify(tagService, times(1)).updateTag(tagId, updatedTagDto);
    }

    @Test
    void testDeleteTag() {
        Long tagId = 1L;
        doNothing().when(tagService).deleteTag(tagId);

        ResponseEntity<Void> response = tagController.deleteTag(tagId);

        assertEquals(204, response.getStatusCodeValue());
        verify(tagService, times(1)).deleteTag(tagId);
    }
}