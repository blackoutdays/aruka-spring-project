package com.mjc.school.aruka.service;

import com.mjc.school.aruka.dto.NewsTagDto;
import com.mjc.school.aruka.mapper.NewsTagMapper;
import com.mjc.school.aruka.model.NewsTag;
import com.mjc.school.aruka.repository.NewsRepository;
import com.mjc.school.aruka.repository.NewsTagRepository;
import com.mjc.school.aruka.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsTagService {

    private static final Logger logger = LoggerFactory.getLogger(NewsTagService.class);

    private final NewsTagRepository newsTagRepository;
    private final NewsRepository newsRepository;
    private final TagRepository tagRepository;
    private final NewsTagMapper newsTagMapper;

    public NewsTagService(NewsTagRepository newsTagRepository, NewsRepository newsRepository, TagRepository tagRepository, NewsTagMapper newsTagMapper) {
        this.newsTagRepository = newsTagRepository;
        this.newsRepository = newsRepository;
        this.tagRepository = tagRepository;
        this.newsTagMapper = newsTagMapper;
    }

    public List<NewsTagDto> getAllNewsTags() {
        return newsTagRepository.findAll().stream()
                .map(newsTagMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<NewsTagDto> getNewsTagsWithPagination(Long newsId, Long tagId, Pageable pageable) {
        return newsTagRepository.findAllByFilters(newsId, tagId, pageable)
                .map(newsTagMapper::toDto);
    }

    public NewsTagDto createNewsTag(NewsTagDto newsTagDto) {
        boolean exists = newsTagRepository.existsByNewsIdAndTagId(newsTagDto.getNewsId(), newsTagDto.getTagId());
        if (exists) {
            throw new RuntimeException("The relationship between News ID " + newsTagDto.getNewsId() +
                    " and Tag ID " + newsTagDto.getTagId() + " already exists.");
        }

        NewsTag newsTag = newsTagMapper.toEntity(newsTagDto);
        newsTag.setNews(newsRepository.findById(newsTagDto.getNewsId())
                .orElseThrow(() -> new RuntimeException("News not found with ID: " + newsTagDto.getNewsId())));
        newsTag.setTag(tagRepository.findById(newsTagDto.getTagId())
                .orElseThrow(() -> new RuntimeException("Tag not found with ID: " + newsTagDto.getTagId())));

        return newsTagMapper.toDto(newsTagRepository.save(newsTag));
    }

    public NewsTagDto updateNewsTag(Long id, NewsTagDto newsTagDto) {
        NewsTag newsTag = newsTagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NewsTag not found with ID: " + id));
        newsTag.setNews(newsRepository.findById(newsTagDto.getNewsId())
                .orElseThrow(() -> new RuntimeException("News not found with ID: " + newsTagDto.getNewsId())));
        newsTag.setTag(tagRepository.findById(newsTagDto.getTagId())
                .orElseThrow(() -> new RuntimeException("Tag not found with ID: " + newsTagDto.getTagId())));
        return newsTagMapper.toDto(newsTagRepository.save(newsTag));
    }

    public void deleteNewsTag(Long id) {
        newsTagRepository.deleteById(id);
    }
}