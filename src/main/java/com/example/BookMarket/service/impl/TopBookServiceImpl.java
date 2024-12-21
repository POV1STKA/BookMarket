package com.example.BookMarket.service.impl;

import com.example.BookMarket.featureToggle.FeatureToggles;
import com.example.BookMarket.featureToggle.annotation.FeatureToggle;
import com.example.BookMarket.service.ProductService;
import com.example.BookMarket.domain.Product;
import com.example.BookMarket.service.TopBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TopBookServiceImpl implements TopBookService {

    private ProductService productService;

    TopBookServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @FeatureToggle(FeatureToggles.TOP_BOOKS)
    public List<String> getTopBooks() {
        List<Product> allProducts = productService.getAllProducts();
        return allProducts.stream()
                .limit(2)
                .map(Product::getTitle)
                .toList();
    }
}