package com.example.BookMarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.BookMarket.domain.Product;
import com.example.BookMarket.service.exception.CustomerHasNoRulesToDeleteProductException;
import com.example.BookMarket.service.exception.CustomerHasNoRulesToUpdateProductException;
import com.example.BookMarket.service.exception.CustomerNotFoundException;
import com.example.BookMarket.service.exception.ProductAlreadyExistsException;
import com.example.BookMarket.service.exception.ProductNotFoundException;
import com.example.BookMarket.service.exception.ProductsNotFoundException;
import com.example.BookMarket.service.impl.CustomerServiceImpl;
import com.example.BookMarket.service.impl.ProductServiceImpl;

@SpringBootTest(classes = { ProductServiceImpl.class, CustomerServiceImpl.class })
@DisplayName("Product Service Tests")
@TestMethodOrder(OrderAnnotation.class)
public class ProductServiceTest {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    private static List<Product> products;
    private static UUID newProductId;
    private static Integer expectedProductsSize = 3;
    private static String newBookTitle = "Harry Potter 1";
    private static String updatedBookTitle = "Harry Potter 2";
    private static Long FirstBookOwnerId = 1L;
    private static Long SecondBookOwnerId = 2L;

    static Stream<Product> provideProducts() {
        return products.stream();
    }

    @Test
    @Order(1)
    void shouldReturnAllProducts() {
        products = productService.getAllProducts();
        assertNotNull(products);
        assertEquals(expectedProductsSize, products.size());
    }

    @ParameterizedTest
    @MethodSource("provideProducts")
    @Order(2)
    void shouldReturnProductByID(Product expectedProduct) {
        Product actualProduct = productService.getProductById(expectedProduct.getId());
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    @Order(3)
    void shouldCreateProduct() {
        Product expectedProduct = Product.builder()
                .id(UUID.randomUUID())
                .title(newBookTitle)
                .bookOwner(customerService.getCustomerById(FirstBookOwnerId))
                .build();

        assertThrows(CustomerNotFoundException.class,
                () -> productService.createProduct(expectedProduct, -1L));

        newProductId = productService.createProduct(expectedProduct, FirstBookOwnerId);
        Product actualProduct = productService.getProductById(newProductId);

        assertNotNull(actualProduct);
        assertEquals(expectedProduct.getTitle(), actualProduct.getTitle());
        assertEquals(expectedProduct.getBookOwner(), actualProduct.getBookOwner());
        assertThrows(ProductAlreadyExistsException.class,
                () -> productService.createProduct(actualProduct, FirstBookOwnerId));
    }

    @Test
    @Order(4)
    void shouldUpdateProduct() {
        Product expectedProduct = Product.builder()
                .id(newProductId)
                .title(updatedBookTitle)
                .bookOwner(customerService.getCustomerById(FirstBookOwnerId))
                .build();

        productService.updateProduct(expectedProduct, FirstBookOwnerId);
        Product actualProduct = productService.getProductById(newProductId);

        assertNotNull(actualProduct);
        assertEquals(expectedProduct.getTitle(), actualProduct.getTitle());
        assertEquals(expectedProduct.getBookOwner(), actualProduct.getBookOwner());
        assertThrows(CustomerHasNoRulesToUpdateProductException.class,
                () -> productService.updateProduct(actualProduct, SecondBookOwnerId));
    }

    @Test
    @Order(4)
    void throwsExceptionWhenUpdatingProductWithExistingName() {
        Product productWithExistingName = Product.builder()
                .id(newProductId)
                .title(products.get(0).getTitle())
                .bookOwner(customerService.getCustomerById(FirstBookOwnerId))
                .build();
        assertThrows(ProductAlreadyExistsException.class,
                () -> productService.updateProduct(productWithExistingName, FirstBookOwnerId));
    }

    @Test
    @Order(5)
    void shouldDeleteProduct() {
        assertThrows(CustomerHasNoRulesToDeleteProductException.class,
                () -> productService.deleteProductById(newProductId, SecondBookOwnerId));

        assertThrows(ProductNotFoundException.class,
                () -> productService.deleteProductById(UUID.randomUUID(), SecondBookOwnerId));

        productService.deleteProductById(newProductId, FirstBookOwnerId);
        assertThrows(ProductNotFoundException.class,
                () -> productService.getProductById(newProductId));
    }

    @Test
    @Order(6)
    void shouldThrowProductsNotFound() {
        List<Product> productsCopy = new ArrayList<>(products);
        for (Product p : productsCopy) {
            productService.deleteProductById(p.getId(), p.getBookOwner().getId());
        }
        assertThrows(ProductsNotFoundException.class,
                () -> productService.getAllProducts());
    }
}
