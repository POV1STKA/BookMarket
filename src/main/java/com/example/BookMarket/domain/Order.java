package com.example.BookMarket.domain;

import java.util.UUID;
import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Order {
	UUID id;

	Customer customer;
	List<Product> products;
}
