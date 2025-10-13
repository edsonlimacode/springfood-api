package com.springfood.api.dto.product;

import com.springfood.api.dto.restaurant.RestaurantIdRequestDto;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDto {

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    private Boolean status = Boolean.TRUE;

    @Valid
    @NotNull
    private RestaurantIdRequestDto restaurant;

}
