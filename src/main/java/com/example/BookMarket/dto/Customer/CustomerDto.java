package com.example.BookMarket.dto.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CustomerDto {

    @NotBlank(message = "Customer nickname could not be blank")
    @Size(min = 5, message = "Customer nickname could not be shorter than 5 characters")
    @Size(max = 30, message = "Customer nickname could not be longer than 30 characters")
    String nickname;

    @NotBlank(message = "Customer address could not be blank")
    @Size(min = 5, message = "Customer address could not be shorter than 5 characters")
    @Size(max = 100, message = "Customer address could not be longer than 100 characters")
    String address;

    @NotBlank(message = "Customer email could not be blank")
    @Email(message = "Customer email should be valid")
    String email;
}
