package com.example.BookMarket.service.exception;

public class CustomerNotFoundException extends RuntimeException {

    private static final String baseMessage = "Customer with id %d not found";

    public CustomerNotFoundException(Long id){
        super(String.format(baseMessage, id));
    }

}