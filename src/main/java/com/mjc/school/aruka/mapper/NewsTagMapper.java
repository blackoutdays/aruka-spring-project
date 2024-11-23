package com.mjc.school.aruka.mapper;

import com.mjc.school.aruka.dto.NewsTagDto;
import com.mjc.school.aruka.model.NewsTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsTagMapper {

    @Mapping(source = "news.id", target = "newsId")
    @Mapping(source = "tag.id", target = "tagId")
    NewsTagDto toDto(NewsTag newsTag);

    @Mapping(source = "newsId", target = "news.id")
    @Mapping(source = "tagId", target = "tag.id")
    NewsTag toEntity(NewsTagDto newsTagDto);
}