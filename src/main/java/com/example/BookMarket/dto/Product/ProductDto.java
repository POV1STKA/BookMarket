package com.example.BookMarket.dto.Product;

import com.example.BookMarket.dto.Genre.GenreDto;
import jakarta.validation.GroupSequence;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@GroupSequence({ProductDto.class})
public class ProductDto {

    @Size(min = 2, message = "Product title could not be shorter than 2 characters")
    @Size(max = 50, message = "Product title could not be longer than 50 characters")
    String title;

    @Size(min = 20, message = "Product description could not be shorter than 20 characters")
    @Size(max = 500, message = "Product description could not be longer than 500 characters")
    String description;

    @Size(min = 2, message = "Author could not be shorter than 2 characters")
    @Size(max = 50, message = "Author could not be longer than 50 characters")
    String author;

    @Min(value = 0, message = "Price could not be less than 0")
    @Max(value = 999_999, message = "Price could not exceed 999 999")
    int price;

    @Valid GenreDto genre;
}
