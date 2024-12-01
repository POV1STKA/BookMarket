package com.example.BookMarket.service.exception;


public class ProductsNotFoundException extends RuntimeException {
    private static final String baseMessage = "There are no products";

    public ProductsNotFoundException(){
        super(baseMessage);
    }
}
