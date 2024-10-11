package iitu.edu.kz.aruka.mapper;

import iitu.edu.kz.aruka.dto.NewsDto;
import iitu.edu.kz.aruka.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsDto toDto(News news);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "author", ignore = true)
    News toEntity(NewsDto newsDto);
}