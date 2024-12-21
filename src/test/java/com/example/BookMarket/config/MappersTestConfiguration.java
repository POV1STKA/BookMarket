package com.example.BookMarket.config;


import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.example.BookMarket.service.mappers.GenreMapper;
import com.example.BookMarket.service.mappers.ProductMapper;

@TestConfiguration
public class MappersTestConfiguration {

    @Bean
    public ProductMapper paymentMapper() {
        return Mappers.getMapper(ProductMapper.class);
    }

    @Bean
    public GenreMapper genreMapper() {
        return Mappers.getMapper(GenreMapper.class);
    }
}

