package com.sm.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderDTO {

    @NotNull(message = "User id required")
    private Long userId;

    @NotNull(message = "Product id required")
    private Long productId;

    @Positive(message = "Quantity must be greater than 0")
    private int qty;
}

