package iitu.edu.kz.aruka.controller;

import iitu.edu.kz.aruka.dto.AuthorNewsDto;
import iitu.edu.kz.aruka.service.AuthorNewsService;
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
@RequestMapping("/api/author-news")
@Validated
public class AuthorNewsController {

    private final AuthorNewsService authorNewsService;
    private static final Logger logger = LoggerFactory.getLogger(AuthorNewsController.class);

    public AuthorNewsController(AuthorNewsService authorNewsService) {
        this.authorNewsService = authorNewsService;
    }

    @Operation(summary = "Get all author news entries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched all author news entries successfully")
    })
    @GetMapping
    public ResponseEntity<List<AuthorNewsDto>> getAllAuthorNews() {
        logger.info("Fetching all author news entries");
        List<AuthorNewsDto> authorNewsList = authorNewsService.getAllAuthorNews();
        return ResponseEntity.ok(authorNewsList);
    }

    @Operation(summary = "Create a new author news entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author news created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<AuthorNewsDto> createAuthorNews(@Valid @RequestBody AuthorNewsDto authorNewsDto) {
        logger.info("Creating author news: {}", authorNewsDto);
        AuthorNewsDto createdAuthorNews = authorNewsService.createAuthorNews(authorNewsDto);
        return new ResponseEntity<>(createdAuthorNews, HttpStatus.CREATED);
    }

    @Operation(summary = "Get an author news entry by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author news entry fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Author news entry not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuthorNewsDto> getAuthorNewsById(@PathVariable Long id) {
        logger.info("Fetching author news entry by ID: {}", id);
        AuthorNewsDto authorNews = authorNewsService.getAuthorNewsById(id);
        return ResponseEntity.ok(authorNews);
    }

    @Operation(summary = "Update an author news entry by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author news updated successfully"),
            @ApiResponse(responseCode = "404", description = "Author news entry not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AuthorNewsDto> updateAuthorNews(@PathVariable Long id, @Valid @RequestBody AuthorNewsDto authorNewsDto) {
        logger.info("Updating author news entry with ID: {}", id);
        AuthorNewsDto updatedAuthorNews = authorNewsService.updateAuthorNews(id, authorNewsDto);
        return ResponseEntity.ok(updatedAuthorNews);
    }

    @Operation(summary = "Delete an author news entry by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Author news deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Author news entry not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorNews(@PathVariable Long id) {
        logger.info("Deleting author news entry with ID: {}", id);
        authorNewsService.deleteAuthorNews(id);
        return ResponseEntity.noContent().build();
    }
}
//package iitu.edu.kz.aruka.controller;
//
//import iitu.edu.kz.aruka.dto.AuthorNewsDto;
//import iitu.edu.kz.aruka.service.AuthorNewsService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.ui.Model;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/author-news")
//@Validated
//public class AuthorNewsController {
//
//    private final AuthorNewsService authorNewsService;
//
//    public AuthorNewsController(AuthorNewsService authorNewsService) {
//        this.authorNewsService = authorNewsService;
//    }
//
//    @GetMapping("/authors")
//    public String getAuthorList(Model model) {
//        List<AuthorNewsDto> authorList = authorNewsService.getAllAuthorNews();
//        model.addAttribute("authorList", authorList);
//        return "authors";
//    }
//
//    @Operation(summary = "Create a new author news entry")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Author news created successfully"),
//            @ApiResponse(responseCode = "400", description = "Invalid input data")
//    })
//    @PostMapping
//    public ResponseEntity<AuthorNewsDto> createAuthorNews(@Valid @RequestBody AuthorNewsDto authorNewsDto) {
//        AuthorNewsDto createdAuthorNews = authorNewsService.createAuthorNews(authorNewsDto);
//        return new ResponseEntity<>(createdAuthorNews, HttpStatus.CREATED);
//    }
//
//    @Operation(summary = "Get all author news entries")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Fetched all author news entries successfully")
//    })
//    @GetMapping
//    public ResponseEntity<List<AuthorNewsDto>> getAllAuthorNews() {
//        List<AuthorNewsDto> authorNewsList = authorNewsService.getAllAuthorNews();
//        return ResponseEntity.ok(authorNewsList);
//    }
//
//    @Operation(summary = "Get an author news entry by ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Author news entry fetched successfully"),
//            @ApiResponse(responseCode = "404", description = "Author news entry not found")
//    })
//    @GetMapping("/{id}")
//    public ResponseEntity<AuthorNewsDto> getAuthorNewsById(@PathVariable Long id) {
//        AuthorNewsDto authorNews = authorNewsService.getAuthorNewsById(id);
//        return ResponseEntity.ok(authorNews);
//    }
//
//    @Operation(summary = "Update an author news entry by ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Author news updated successfully"),
//            @ApiResponse(responseCode = "404", description = "Author news entry not found"),
//            @ApiResponse(responseCode = "400", description = "Invalid input data")
//    })
//    @PutMapping("/{id}")
//    public ResponseEntity<AuthorNewsDto> updateAuthorNews(@PathVariable Long id, @Valid @RequestBody AuthorNewsDto authorNewsDto) {
//        AuthorNewsDto updatedAuthorNews = authorNewsService.updateAuthorNews(id, authorNewsDto);
//        return ResponseEntity.ok(updatedAuthorNews);
//    }
//
//    @Operation(summary = "Delete an author news entry by ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "Author news deleted successfully"),
//            @ApiResponse(responseCode = "404", description = "Author news entry not found")
//    })
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAuthorNews(@PathVariable Long id) {
//        authorNewsService.deleteAuthorNews(id);
//        return ResponseEntity.noContent().build();
//    }
//}