package com.customerService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

public record CustomerDto(
        long id,

        @NotEmpty(message = "Name is required")
        @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name must contain only letters and spaces")
        @Length(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,

        @NotEmpty(message = "Email is required")
        @Email(message = "Email is valid")
        String email,

        @NotEmpty(message = "Phone is required")
        @Pattern(regexp = "^\\d{10}$", message = "Phone is invalid")
        String phoneNumber

) {
}
