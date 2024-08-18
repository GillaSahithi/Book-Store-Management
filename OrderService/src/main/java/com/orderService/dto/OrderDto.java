package com.orderService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record OrderDto(
        Long id,

        //@NotNull(message = "CustomerId is mandatory")
        Long customerId,

        //@NotNull(message = "BookId is mandatory")
        Long bookId,

        //@NotNull(message = "Quantity cannot be null")
        @Min(value = 1, message = "Quantity must be at least 1")
        int quantity,

        //@NotNull(message = "Status cannot be null")
        @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED", message = "Invalid status. Must be one of: PENDING, CONFIRMED, CANCELLED")
        String status
) {
}
