package com.mjc.school.aruka.service;

import com.mjc.school.aruka.dto.NewsDto;
import com.mjc.school.aruka.exception.ResourceNotFoundException;
import com.mjc.school.aruka.mapper.NewsMapper;
import com.mjc.school.aruka.model.News;
import com.mjc.school.aruka.model.AuthorNews;
import com.mjc.school.aruka.model.Tag;
import com.mjc.school.aruka.repository.NewsRepository;
import com.mjc.school.aruka.repository.AuthorNewsRepository;
import com.mjc.school.aruka.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final AuthorNewsRepository authorNewsRepository;
    private final TagRepository tagRepository;
    private final NewsMapper newsMapper;

    public NewsService(NewsRepository newsRepository, AuthorNewsRepository authorNewsRepository, TagRepository tagRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.authorNewsRepository = authorNewsRepository;
        this.tagRepository = tagRepository;
        this.newsMapper = newsMapper;
    }

    public NewsDto createNews(NewsDto newsDto) {
        AuthorNews author = authorNewsRepository.findById(newsDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + newsDto.getAuthorId()));

        List<Tag> tags = newsDto.getTags().stream().map(tagName -> {
            Tag tag = tagRepository.findByName(tagName).orElseGet(() -> {
                Tag newTag = new Tag();
                newTag.setName(tagName);
                return tagRepository.save(newTag);
            });
            return tag;
        }).collect(Collectors.toList());

        News news = newsMapper.toEntity(newsDto);
        news.setAuthor(author);
        news.setTags(tags);
        news.setCreatedDate(LocalDateTime.now());
        news.setLastUpdatedDate(LocalDateTime.now());

        return newsMapper.toDto(newsRepository.save(news));
    }

    public List<NewsDto> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
    }

    public NewsDto getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found with ID: " + id));
        return newsMapper.toDto(news);
    }

    public Page<NewsDto> getPaginatedNews(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return newsRepository.findAll(pageable).map(newsMapper::toDto);
    }
    public NewsDto updateNews(Long id, NewsDto newsDto) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found with ID: " + id));

        AuthorNews author = authorNewsRepository.findById(newsDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + newsDto.getAuthorId()));

        List<Tag> tags = newsDto.getTags().stream().map(tagName -> {
            Tag tag = tagRepository.findByName(tagName).orElseGet(() -> {
                Tag newTag = new Tag();
                newTag.setName(tagName);
                return tagRepository.save(newTag);
            });
            return tag;
        }).collect(Collectors.toList());

        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setLastUpdatedDate(LocalDateTime.now());
        news.setAuthor(author);
        news.setTags(tags);

        return newsMapper.toDto(newsRepository.save(news));
    }

    public Page<NewsDto> searchNews(String authorName, String title, String content, List<String> tagNames, Pageable pageable) {
        Page<News> filteredNews = newsRepository.searchNews(authorName, title, content, tagNames, pageable);
        return filteredNews.map(newsMapper::toDto);
    }
    public boolean deleteNews(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new ResourceNotFoundException("News not found with ID: " + id);
        }
        newsRepository.deleteById(id);
        return true;
    }
}