package com.example.BookMarket.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.BookMarket.domain.Order;
import com.example.BookMarket.dto.Order.OrderDto;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "products", source = "products")
    OrderDto toOrderDto(Order order);
}
