package com.springfood.api.dto.restaurant;


import com.springfood.api.dto.address.AddressRequestDto;
import com.springfood.api.dto.kitchen.KitchenIdRequestDto;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantRequestDto {

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal delivery;

    @NotNull
    @Valid
    private KitchenIdRequestDto kitchen;

    @NotNull
    @Valid
    private AddressRequestDto address;

}
