package com.springfood.api.dto.city;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CityIdRequestDto {

    @NotNull
    private Long id;
}
