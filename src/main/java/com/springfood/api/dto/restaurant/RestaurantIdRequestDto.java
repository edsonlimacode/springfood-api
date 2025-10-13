package com.springfood.api.dto.restaurant;


import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class RestaurantIdRequestDto {

    @NotNull
    private Long id;
}
