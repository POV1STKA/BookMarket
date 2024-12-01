package com.example.BookMarket.service.mappers;

import com.example.BookMarket.domain.Customer;
import com.example.BookMarket.dto.Customer.CustomerDto;
import com.example.BookMarket.dto.Customer.CustomerEntry;
import com.example.BookMarket.dto.Customer.CustomerListDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = GenreMapper.class)
public interface CustomerMapper {

    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "email", source = "email")
    CustomerDto toCustomerDto(Customer customer);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "email", source = "email")
    CustomerEntry toCustomerEntry(Customer customer);

    default CustomerListDto toCustomerListDto(List<Customer> customers) {
        return CustomerListDto.builder().customers(toCustomerEntries(customers)).build();
    }

    List<CustomerEntry> toCustomerEntries(List<Customer> customers);
}