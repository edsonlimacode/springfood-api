package com.springfood.api.dto.orderItem;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class OrderItemRequestDto {

    @NotNull
    private Long productId;

    @NotNull
    @PositiveOrZero
    private Integer quantity;
}
