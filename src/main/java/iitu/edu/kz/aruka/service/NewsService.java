package iitu.edu.kz.aruka.service;

import iitu.edu.kz.aruka.dto.NewsDto;
import iitu.edu.kz.aruka.model.News;
import iitu.edu.kz.aruka.model.AuthorNews;
import iitu.edu.kz.aruka.repository.NewsRepository;
import iitu.edu.kz.aruka.repository.AuthorNewsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final AuthorNewsRepository authorNewsRepository;

    public NewsService(NewsRepository newsRepository, AuthorNewsRepository authorNewsRepository) {
        this.newsRepository = newsRepository;
        this.authorNewsRepository = authorNewsRepository;
    }

    public NewsDto createNews(NewsDto newsDto) {
        AuthorNews author = authorNewsRepository.findById(newsDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        News news = new News();
        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setAuthor(author);
        news.setCreatedDate(LocalDateTime.now());
        news.setLastUpdatedDate(LocalDateTime.now());

        news = newsRepository.save(news);
        return mapToDto(news);
    }

    public List<NewsDto> getAllNews() {
        return newsRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public NewsDto getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));
        return mapToDto(news);
    }

    public NewsDto updateNews(Long id, NewsDto newsDto) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));

        AuthorNews author = authorNewsRepository.findById(newsDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setLastUpdatedDate(LocalDateTime.now());
        news.setAuthor(author);

        news = newsRepository.save(news);
        return mapToDto(news);
    }

    public boolean deleteNews(Long id) {
        newsRepository.deleteById(id);
        return true;
    }

    private NewsDto mapToDto(News news) {
        NewsDto dto = new NewsDto();
        dto.setId(news.getId());
        dto.setTitle(news.getTitle());
        dto.setContent(news.getContent());
        dto.setAuthorId(news.getAuthor().getId());
        return dto;
    }
}