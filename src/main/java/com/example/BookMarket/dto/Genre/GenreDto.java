package com.example.BookMarket.dto.Genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import java.util.UUID;

@Value
@Builder
@Jacksonized
public class GenreDto {
    UUID id;

    @NotBlank(message = "Genre name could not be blank")
    @Size(min = 2, message = "Genre name could not be shorter than 2 characters")
    @Size(max = 50, message = "Genre name could not be longer than 50 characters")
    String name;
}