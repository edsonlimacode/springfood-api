package com.springfood.api.dto.restaurant;


import com.springfood.api.dto.address.AddressResponseDto;
import com.springfood.api.dto.kitchen.KitchenResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantResponseDto {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Thai Delight")
    private String name;
    @Schema(example = "5.50")
    private BigDecimal delivery;
    @Schema(example = "true")
    private Boolean status;
    private KitchenResponseDto kitchen;
    private AddressResponseDto address;

}
