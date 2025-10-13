package com.springfood.api.dto.payment;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class PaymentRequestDto {

    @Schema(required = true, example = "Cartão de crédito")
    @NotBlank
    private String description;

}
