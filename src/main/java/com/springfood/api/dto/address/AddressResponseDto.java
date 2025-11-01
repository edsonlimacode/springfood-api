package com.springfood.api.dto.address;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDto {

    @Schema(example = "12345678")
    private String cep;

    @Schema(example = "Av. Paulista")
    private String street;

    @Schema(example = "1000")
    private String number;

    @Schema(example = "Apt 101")
    private String complement;

    @Schema(example = "Bela Vista")
    private String neighborhood;

    @Schema(example = "São Paulo")
    private String city;

    @Schema(example = "SP")
    private String state;
}
