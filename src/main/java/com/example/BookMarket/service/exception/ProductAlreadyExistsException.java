package com.example.BookMarket.service.exception;

public class ProductAlreadyExistsException extends RuntimeException {
    private static final String baseMessage = "Product with title %s already exists";

    public ProductAlreadyExistsException(String title) {
        super(String.format(baseMessage, title));
    }
}