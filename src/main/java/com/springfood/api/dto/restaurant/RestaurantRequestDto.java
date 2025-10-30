package com.springfood.api.dto.restaurant;


import com.springfood.api.dto.address.AddressRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

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
    private Long kitchenId;

    @NotNull
    @Valid
    private AddressRequestDto address;


}
