package com.mjc.school.aruka.service;

import com.mjc.school.aruka.dto.CommentDto;
import com.mjc.school.aruka.mapper.CommentMapper;
import com.mjc.school.aruka.model.Comment;
import com.mjc.school.aruka.model.News;
import com.mjc.school.aruka.repository.CommentRepository;
import com.mjc.school.aruka.repository.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, NewsRepository newsRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.newsRepository = newsRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDto> getCommentsByNewsId(Long newsId) {
        return commentRepository.findByNewsId(newsId).stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<CommentDto> getCommentsWithPagination(Pageable pageable) {
        return commentRepository.findAll(pageable).map(commentMapper::toDto);
    }

    public CommentDto createComment(CommentDto commentDto) {
        News news = newsRepository.findById(commentDto.getNewsId())
                .orElseThrow(() -> new RuntimeException("News not found with ID: " + commentDto.getNewsId()));

        Comment comment = commentMapper.toEntity(commentDto);
        comment.setNews(news);

        return commentMapper.toDto(commentRepository.save(comment));
    }

    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("Comment not found with ID: " + id);
        }
        commentRepository.deleteById(id);
    }

    public CommentDto updateComment(Long id, CommentDto commentDto) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + id));

        News news = newsRepository.findById(commentDto.getNewsId())
                .orElseThrow(() -> new RuntimeException("News not found with ID: " + commentDto.getNewsId()));

        existingComment.setContent(commentDto.getContent());
        existingComment.setNews(news);

        return commentMapper.toDto(commentRepository.save(existingComment));
    }

    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + id));
        return commentMapper.toDto(comment);
    }

    public Page<CommentDto> getSortedComments(Pageable pageable, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return commentRepository.findAll(sortedPageable).map(commentMapper::toDto);
    }
}