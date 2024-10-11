//package iitu.edu.kz.aruka.controller;
//
//import iitu.edu.kz.aruka.dto.NewsDto;
//import iitu.edu.kz.aruka.service.NewsService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class NewsControllerTest {
//
//    @Mock
//    private NewsService newsService;
//
//    @InjectMocks
//    private NewsController newsController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetAllNews() {
//        NewsDto newsDto1 = new NewsDto();
//        newsDto1.setTitle("Title 1");
//        newsDto1.setContent("Content 1");
//
//        NewsDto newsDto2 = new NewsDto();
//        newsDto2.setTitle("Title 2");
//        newsDto2.setContent("Content 2");
//
//        when(newsService.getAllNews()).thenReturn(Arrays.asList(newsDto1, newsDto2));
//
//        ResponseEntity<List<NewsDto>> response = newsController.getAllNews();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        assertNotNull(response.getBody());
//        assertEquals(2, response.getBody().size());
//        assertEquals("Title 1", response.getBody().get(0).getTitle());
//    }
//
//    @Test
//    public void testCreateNews() {
//        NewsDto newsDto = new NewsDto();
//        newsDto.setTitle("Test Title");
//        newsDto.setContent("Test Content");
//
//        when(newsService.createNews(any(NewsDto.class))).thenReturn(newsDto);
//
//        ResponseEntity<NewsDto> response = newsController.createNews(newsDto);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//
//        assertNotNull(response.getBody());
//        assertEquals("Test Title", response.getBody().getTitle());
//    }
//
//    @Test
//    public void testGetNewsById() {
//        NewsDto newsDto = new NewsDto();
//        newsDto.setTitle("Test Title");
//        newsDto.setContent("Test Content");
//
//        when(newsService.getNewsById(anyLong())).thenReturn(newsDto);
//
//        ResponseEntity<NewsDto> response = newsController.getNewsById(1L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        assertNotNull(response.getBody());
//        assertEquals("Test Title", response.getBody().getTitle());
//    }
//
//    @Test
//    public void testUpdateNews() {
//        NewsDto newsDto = new NewsDto();
//        newsDto.setTitle("Updated Title");
//        newsDto.setContent("Updated Content");
//
//        when(newsService.updateNews(anyLong(), any(NewsDto.class))).thenReturn(newsDto);
//
//        ResponseEntity<NewsDto> response = newsController.updateNews(1L, newsDto);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        assertNotNull(response.getBody());
//        assertEquals("Updated Title", response.getBody().getTitle());
//    }
//
//    @Test
//    public void testDeleteNews() {
//        ResponseEntity<Void> response = newsController.deleteNews(1L);
//
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//
//        verify(newsService, times(1)).deleteNews(1L);
//    }
//}