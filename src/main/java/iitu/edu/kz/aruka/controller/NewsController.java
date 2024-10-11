package iitu.edu.kz.aruka.controller;

import iitu.edu.kz.aruka.dto.NewsDto;
import iitu.edu.kz.aruka.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//package iitu.edu.kz.aruka.controller;
//
//import iitu.edu.kz.aruka.dto.NewsDto;
//import iitu.edu.kz.aruka.service.NewsService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/news")
//@Validated
//public class NewsController {
//
//    private final NewsService newsService;
//
//    public NewsController(NewsService newsService) {
//        this.newsService = newsService;
//    }
//
////    @Operation(summary = "Get list of all news")
////    @GetMapping("/all")
////    public String getNewsList(Model model) {
////        List<NewsDto> newsList = newsService.getAllNews();
////        model.addAttribute("newsList", newsList);
////        return "news";
////    }
//
//    @Operation(summary = "Create a new news entry")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "News created successfully"),
//            @ApiResponse(responseCode = "400", description = "Invalid input data")
//    })
//    @PostMapping
//    public ResponseEntity<NewsDto> createNews(@Valid @RequestBody NewsDto newsDto) {
//        NewsDto createdNews = newsService.createNews(newsDto);
//        return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
//    }
//
//    @Operation(summary = "Get all news entries")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Fetched all news entries successfully")
//    })
//    @GetMapping
//    public ResponseEntity<List<NewsDto>> getAllNews() {
//        List<NewsDto> newsList = newsService.getAllNews();
//        return ResponseEntity.ok(newsList);
//    }
//
//    @Operation(summary = "Get a news entry by ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "News entry fetched successfully"),
//            @ApiResponse(responseCode = "404", description = "News entry not found")
//    })
//    @GetMapping("/{id}")
//    public ResponseEntity<NewsDto> getNewsById(@PathVariable Long id) {
//        NewsDto news = newsService.getNewsById(id);
//        return ResponseEntity.ok(news);
//    }
//
//    @Operation(summary = "Update a news entry by ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "News updated successfully"),
//            @ApiResponse(responseCode = "404", description = "News entry not found"),
//            @ApiResponse(responseCode = "400", description = "Invalid input data")
//    })
//    @PutMapping("/{id}")
//    public ResponseEntity<NewsDto> updateNews(@PathVariable Long id, @Valid @RequestBody NewsDto newsDto) {
//        NewsDto updatedNews = newsService.updateNews(id, newsDto);
//        return ResponseEntity.ok(updatedNews);
//    }
//
//    @Operation(summary = "Delete a news entry by ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "News deleted successfully"),
//            @ApiResponse(responseCode = "404", description = "News entry not found")
//    })
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
//        newsService.deleteNews(id);
//        return ResponseEntity.noContent().build();
//    }
//}