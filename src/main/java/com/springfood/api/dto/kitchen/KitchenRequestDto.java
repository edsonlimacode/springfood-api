package com.springfood.api.dto.kitchen;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class KitchenRequestDto {

    @NotBlank
    private String name;
}
