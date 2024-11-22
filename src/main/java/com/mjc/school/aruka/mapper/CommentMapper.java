package com.mjc.school.aruka.mapper;

import com.mjc.school.aruka.dto.CommentDto;
import com.mjc.school.aruka.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "news.id", target = "newsId")
    CommentDto toDto(Comment comment);

    @Mapping(source = "newsId", target = "news.id")
    Comment toEntity(CommentDto commentDto);
}