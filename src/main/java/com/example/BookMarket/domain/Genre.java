package com.example.BookMarket.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Genre {
    int id;
    String name;
}