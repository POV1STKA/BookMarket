package com.example.BookMarket.service;


import com.example.BookMarket.domain.Customer;

public interface CustomerService {
    Customer getCustomerById(Long id);
}