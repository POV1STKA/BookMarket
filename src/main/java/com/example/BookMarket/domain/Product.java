package com.example.BookMarket.domain;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Product {
	UUID id;
	String title;
	String description;
	String author;
	int price;

	Customer bookOwner;
	Genre genre;
}
