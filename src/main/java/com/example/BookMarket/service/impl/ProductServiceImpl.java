package com.example.BookMarket.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.BookMarket.domain.Product;
import com.example.BookMarket.domain.Genre;
import com.example.BookMarket.domain.Customer;
import com.example.BookMarket.service.CustomerService;
import com.example.BookMarket.service.ProductService;
import com.example.BookMarket.service.exception.CustomerHasNoRulesToDeleteProductException;
import com.example.BookMarket.service.exception.CustomerHasNoRulesToUpdateProductException;
import com.example.BookMarket.service.exception.ProductAlreadyExistsException;
import com.example.BookMarket.service.exception.ProductNotFoundException;
import com.example.BookMarket.service.exception.ProductsNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final List<Product> products;
    private final CustomerService customerService;

    public ProductServiceImpl(CustomerService customerService){
        this.customerService = customerService;
        products = buildAllProductsMock();
    }

    @Override
    public UUID createProduct(Product product, Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (products.stream().anyMatch(
                p -> p.getTitle().equals(product.getTitle()))){
            throw new ProductAlreadyExistsException(product.getTitle());
        }
        Product newProduct = product.toBuilder()
                .id(UUID.randomUUID())
                .bookOwner(customer)
                .build();
        products.add(newProduct);
        return newProduct.getId();
    }

    @Override
    public List<Product> getAllProducts() {
        if (products.isEmpty()){
            throw new ProductsNotFoundException();
        }
        return products;
    }

    @Override
    public Product getProductById(UUID id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    log.info("Product with {} id not found in mock", id);
                    return new ProductNotFoundException(id);
                });
    }

    @Override
    public void updateProduct(Product product, Long id) {
        Product existingProduct = getProductById(product.getId());
        if (!existingProduct.getBookOwner().getId().equals(id)){
            throw new CustomerHasNoRulesToUpdateProductException(id, product.getId());
        }
        if (products.stream().anyMatch(
                p -> p.getTitle().equals(product.getTitle()))){
            throw new ProductAlreadyExistsException(product.getTitle());
        }
        Product updatedProduct = existingProduct.toBuilder()
                .title(product.getTitle())
                .description(product.getDescription())
                .author(product.getAuthor())
                .price(product.getPrice())
                .genre(product.getGenre())
                .build();
        products.set(products.indexOf(existingProduct), updatedProduct);
    }

    @Override
    public void deleteProductById(UUID productId, Long requesterId) {
        Product existingProduct = getProductById(productId);
        Customer bookOwner = existingProduct.getBookOwner();
        if (!bookOwner.getId().equals(requesterId)){
            throw new CustomerHasNoRulesToDeleteProductException(requesterId, productId);
        }
        products.remove(existingProduct);
    }

    private List<Product> buildAllProductsMock() {
        List<Product> products = new ArrayList<>();

        Genre Detective = Genre.builder()
                .id(UUID.randomUUID())
                .name("Detective")
                .build();
        Genre Parable = Genre.builder()
                .id(UUID.randomUUID())
                .name("Parable")
                .build();

        Customer customer1 = customerService.getCustomerById(1L);
        Customer customer2 = customerService.getCustomerById(2L);

        products.add(Product.builder()
                .id(UUID.randomUUID())
                .title("Animal Farm")
                .description("The tale depicts the evolution of a society of animals driven from a cattle yard by its previous owner")
                .author("George Orwell")
                .price(200)
                .genre(Parable)
                .bookOwner(customer2)
                .build());

        products.add(Product.builder()
                .id(UUID.randomUUID())
                .title("Ten Little Niggers")
                .description("Eight people arrive on a small, isolated island off the Devon coast, each having received an unexpected invitation. ")
                .author("Agatha Mary Clarissa")
                .price(250)
                .genre(Detective)
                .bookOwner(customer1)
                .build());

        products.add(Product.builder()
                .id(UUID.randomUUID())
                .title("Fantomas")
                .description("A brilliant criminal who hides his face, one of the most famous anti-heroes of French literature and cinema.")
                .author("Pierre Souvestre")
                .price(300)
                .genre(Detective)
                .bookOwner(customer1)
                .build());

        return products;
    }

}