package com.mjc.school.aruka.mapper;

import com.mjc.school.aruka.dto.NewsDto;
import com.mjc.school.aruka.model.News;
import com.mjc.school.aruka.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "author", ignore = true)
    News toEntity(NewsDto newsDto);

    NewsDto toDto(News news);

    default List<String> mapTagsToStrings(List<Tag> tags) {
        return tags.stream().map(Tag::getName).collect(Collectors.toList());
    }

    default List<Tag> mapStringsToTags(List<String> tagNames) {
        return tagNames.stream().map(name -> {
            Tag tag = new Tag();
            tag.setName(name);
            return tag;
        }).collect(Collectors.toList());
    }
}