package com.example.BookMarket.service.impl;

import com.example.BookMarket.domain.Customer;
import com.example.BookMarket.service.CustomerService;
import com.example.BookMarket.service.exception.CustomerNotFoundException;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final List<Customer> customers = buildAllCustomersMock();

    @Override
    public Customer getCustomerById(Long id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    log.info("Customer with id %d not found in mock", id);
                    throw new CustomerNotFoundException(id);
                });
    }

    private List<Customer> buildAllCustomersMock() {
        return List.of(
                Customer.builder()
                        .id(1L)
                        .nickname("Ivan Zelinski")
                        .address("84737 Ryan Rapids")
                        .email("ivan.zelinski@gmail.com")
                        .build(),
                Customer.builder()
                        .id(2L)
                        .nickname("Vasil Petrov")
                        .address("61863 McKenzie Circle Suite 987")
                        .email("vasil.petrov@gmail.com")
                        .build()
        );
    }
}