package com.example.BookMarket.dto.Order;

import com.example.BookMarket.dto.Product.ProductListDto;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class OrderDto {
    UUID id;
    ProductListDto products;
}