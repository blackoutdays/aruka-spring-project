package iitu.edu.kz.aruka.mapper;

import iitu.edu.kz.aruka.dto.AuthorNewsDto;
import iitu.edu.kz.aruka.model.AuthorNews;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorNewsMapper {

    AuthorNews toEntity(AuthorNewsDto authorNewsDto);

    AuthorNewsDto toDto(AuthorNews authorNews);
}