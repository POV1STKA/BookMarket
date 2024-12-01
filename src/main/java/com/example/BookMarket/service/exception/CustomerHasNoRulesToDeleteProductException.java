package com.example.BookMarket.service.exception;

import java.util.UUID;

public class CustomerHasNoRulesToDeleteProductException extends RuntimeException {
    private static final String baseMessage =
            "Customer with id %d, has no rules to delete product with id %s";

    public CustomerHasNoRulesToDeleteProductException(Long customerId, UUID productId) {
        super(String.format(baseMessage, customerId, productId));
    }
}
