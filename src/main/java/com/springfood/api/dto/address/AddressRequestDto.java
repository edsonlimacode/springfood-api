package com.springfood.api.dto.address;

import com.springfood.api.dto.city.CityIdRequestDto;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class AddressRequestDto {

    @NotBlank
    private String cep;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    private String complement;
    @NotBlank
    private String neighborhood;
    @Valid
    @NotNull
    private Long cityId;

}
