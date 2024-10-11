package iitu.edu.kz.aruka.service;

import iitu.edu.kz.aruka.dto.AuthorNewsDto;
import iitu.edu.kz.aruka.mapper.AuthorNewsMapper;
import iitu.edu.kz.aruka.model.AuthorNews;
import iitu.edu.kz.aruka.repository.AuthorNewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorNewsService {

    private final AuthorNewsRepository authorNewsRepository;
    private final AuthorNewsMapper authorNewsMapper;
    private static final Logger logger = LoggerFactory.getLogger(AuthorNewsService.class);

    public AuthorNewsService(AuthorNewsRepository authorNewsRepository, AuthorNewsMapper authorNewsMapper) {
        this.authorNewsRepository = authorNewsRepository;
        this.authorNewsMapper = authorNewsMapper;
    }

    public AuthorNewsDto createAuthorNews(@Valid AuthorNewsDto authorNewsDto) {
        logger.info("Creating author news: {}", authorNewsDto);
        AuthorNews authorNews = authorNewsMapper.toEntity(authorNewsDto);
        authorNews = authorNewsRepository.save(authorNews);
        return authorNewsMapper.toDto(authorNews);
    }

    public List<AuthorNewsDto> getAllAuthorNews() {
        logger.info("Fetching all author news entries");
        return authorNewsRepository.findAll().stream()
                .map(authorNewsMapper::toDto)
                .collect(Collectors.toList());
    }

    public AuthorNewsDto getAuthorNewsById(Long id) {
        logger.info("Fetching author news entry by ID: {}", id);
        AuthorNews authorNews = authorNewsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AuthorNews not found"));
        return authorNewsMapper.toDto(authorNews);
    }

    public AuthorNewsDto updateAuthorNews(Long id, @Valid AuthorNewsDto authorNewsDto) {
        logger.info("Updating author news entry with ID: {}", id);
        AuthorNews authorNews = authorNewsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AuthorNews not found"));

        authorNews.setFirstName(authorNewsDto.getFirstName());
        authorNews.setLastName(authorNewsDto.getLastName());
        authorNews.setEmail(authorNewsDto.getEmail());

        authorNews = authorNewsRepository.save(authorNews);
        return authorNewsMapper.toDto(authorNews);
    }

    public void deleteAuthorNews(Long id) {
        logger.info("Deleting author news entry with ID: {}", id);
        authorNewsRepository.deleteById(id);
    }
}






//package iitu.edu.kz.aruka.service;
//
//import iitu.edu.kz.aruka.dto.AuthorNewsDto;
//import iitu.edu.kz.aruka.mapper.AuthorNewsMapper;
//import iitu.edu.kz.aruka.model.AuthorNews;
//import iitu.edu.kz.aruka.repository.AuthorNewsRepository;
//import org.springframework.stereotype.Service;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class AuthorNewsService {
//
//    private final AuthorNewsRepository authorNewsRepository;
//    private final AuthorNewsMapper authorNewsMapper;
//
//    public AuthorNewsService(AuthorNewsRepository authorNewsRepository, AuthorNewsMapper authorNewsMapper) {
//        this.authorNewsRepository = authorNewsRepository;
//        this.authorNewsMapper = authorNewsMapper;
//    }
//
//    public AuthorNewsDto createAuthorNews(@Valid AuthorNewsDto authorNewsDto) {
//        AuthorNews authorNews = authorNewsMapper.toEntity(authorNewsDto);
//        authorNews = authorNewsRepository.save(authorNews);
//        return authorNewsMapper.toDto(authorNews);
//    }
//
//    public List<AuthorNewsDto> getAllAuthorNews() {
//        return authorNewsRepository.findAll().stream()
//                .map(authorNewsMapper::toDto)
//                .collect(Collectors.toList());
//    }
//
//    public AuthorNewsDto getAuthorNewsById(Long id) {
//        AuthorNews authorNews = authorNewsRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("AuthorNews not found"));
//        return authorNewsMapper.toDto(authorNews);
//    }
//
//    public AuthorNewsDto updateAuthorNews(Long id, @Valid AuthorNewsDto authorNewsDto) {
//        AuthorNews authorNews = authorNewsRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("AuthorNews not found"));
//
//        authorNews.setFirstName(authorNewsDto.getFirstName());
//        authorNews.setLastName(authorNewsDto.getLastName());
//        authorNews.setEmail(authorNewsDto.getEmail());
//
//        authorNews = authorNewsRepository.save(authorNews);
//        return authorNewsMapper.toDto(authorNews);
//    }
//
//    public boolean deleteAuthorNews(Long id) {
//        authorNewsRepository.deleteById(id);
//        return true;
//    }
//}