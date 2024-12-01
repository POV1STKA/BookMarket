package com.example.BookMarket.service.mappers;

import com.example.BookMarket.domain.Genre;
import com.example.BookMarket.dto.Genre.GenreDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    GenreDto toGenreDto(Genre genre);
}
