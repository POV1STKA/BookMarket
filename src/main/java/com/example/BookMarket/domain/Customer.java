package com.example.BookMarket.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Customer {
    Long id;
    String nickname;

    String address;
    String email;
}
