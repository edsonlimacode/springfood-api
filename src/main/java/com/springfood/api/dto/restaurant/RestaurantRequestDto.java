package com.springfood.api.dto.restaurant;


import com.springfood.api.dto.address.AddressRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "Restaurante Nordestino")
    @NotBlank
    private String name;

    @Schema(example = "5.50")
    @NotNull
    @PositiveOrZero
    private BigDecimal delivery;

    @Schema(example = "1")
    @NotNull
    private Long kitchenId;

    @NotNull
    @Valid
    private AddressRequestDto address;


}
