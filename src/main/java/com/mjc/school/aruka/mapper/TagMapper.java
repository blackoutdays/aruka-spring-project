package com.mjc.school.aruka.mapper;

import com.mjc.school.aruka.dto.TagDto;
import com.mjc.school.aruka.model.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto toDto(Tag tag);
    Tag toEntity(TagDto tagDto);
}