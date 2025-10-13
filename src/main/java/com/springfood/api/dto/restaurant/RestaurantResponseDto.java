package com.springfood.api.dto.restaurant;


import com.springfood.api.dto.address.AddressResponseDto;
import com.springfood.api.dto.kitchen.KitchenResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantResponseDto {

    private Long id;
    private String name;
    private BigDecimal delivery;
    private Boolean status;
    private KitchenResponseDto kitchen;
    private AddressResponseDto address;

}
