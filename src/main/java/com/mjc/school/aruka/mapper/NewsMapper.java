package com.mjc.school.aruka.mapper;

import com.mjc.school.aruka.dto.NewsDto;
import com.mjc.school.aruka.model.News;
import com.mjc.school.aruka.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "tags", source = "tags", qualifiedByName = "mapStringsToTags")
    News toEntity(NewsDto newsDto);

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "mapTagsToStrings")
    NewsDto toDto(News news);

    @Named("mapTagsToStrings")
    default List<String> mapTagsToStrings(List<Tag> tags) {
        return tags != null
                ? tags.stream().map(Tag::getName).collect(Collectors.toList())
                : null;
    }

    @Named("mapStringsToTags")
    default List<Tag> mapStringsToTags(List<String> tagNames) {
        return tagNames != null
                ? tagNames.stream().map(name -> {
            Tag tag = new Tag();
            tag.setName(name);
            return tag;
        }).collect(Collectors.toList())
                : null;
    }
}