package com.springfood.api.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDto {

    @Schema(example = "01310-000")
    @NotBlank
    private String cep;

    @Schema(example = "Avenida Paulista")
    @NotBlank
    private String street;

    @Schema(example = "1000")
    @NotBlank
    private String number;

    @Schema(example = "Apto 101")
    private String complement;
    @NotBlank

    @Schema(example = "Bela Vista")
    private String neighborhood;

    @Schema(example = "1")
    @NotNull
    private Long cityId;

}
