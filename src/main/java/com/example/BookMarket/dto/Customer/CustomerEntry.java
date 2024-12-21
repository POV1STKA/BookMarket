package com.example.BookMarket.dto.Customer;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CustomerEntry {
    Long id;
    String nickname;
    String address;
    String email;
}