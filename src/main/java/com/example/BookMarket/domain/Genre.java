package com.example.BookMarket.domain;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder
public class Genre {
    UUID id;
    String name;
}