package com.springfood.api.dto.payment;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Cartão de crédito")
    private String description;

}
