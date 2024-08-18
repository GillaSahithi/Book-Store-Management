package com.bookService.dto;

import jakarta.validation.constraints.*;

public record BookDto(

        long id,

        @NotEmpty(message = "Book title is required")
        @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
        String title,

        @NotEmpty(message = "Author name is required")
        @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
        String author,

        @NotNull(message = "Price is mandatory")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        Double price,

        @NotNull(message = "Stock is mandatory")
        @Min(value = 0, message = "Stock must be 0 or more")
        int stock
) {
}

