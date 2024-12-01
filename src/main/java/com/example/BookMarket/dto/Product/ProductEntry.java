package com.example.BookMarket.dto.Product;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ProductEntry {
    UUID id;
    String title;
    String description;
    String author;
    int price;
    String genre;
}
