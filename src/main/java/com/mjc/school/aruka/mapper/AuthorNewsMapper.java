package com.mjc.school.aruka.mapper;

import com.mjc.school.aruka.dto.AuthorNewsDto;
import com.mjc.school.aruka.model.AuthorNews;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorNewsMapper {

    AuthorNews toEntity(AuthorNewsDto authorNewsDto);

    AuthorNewsDto toDto(AuthorNews authorNews);
}