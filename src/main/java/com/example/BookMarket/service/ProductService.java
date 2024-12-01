package com.example.BookMarket.service;

import java.util.List;
import java.util.UUID;

import com.example.BookMarket.domain.Product;

public interface ProductService {
    UUID createProduct(Product product, Long requesterId);
    List<Product> getAllProducts();
    Product getProductById(UUID id);
    void updateProduct(Product product, Long requesterId);
    void deleteProductById(UUID productId, Long requesterId);
}