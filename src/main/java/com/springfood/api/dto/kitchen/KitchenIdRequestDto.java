package com.springfood.api.dto.kitchen;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class KitchenIdRequestDto {

    @NotNull
    private Long id;
}
