package com.example.BookMarket.dto.Product;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ProductListDto {
    List<ProductEntry> products;
}