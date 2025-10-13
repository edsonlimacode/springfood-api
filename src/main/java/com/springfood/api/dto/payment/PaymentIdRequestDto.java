package com.springfood.api.dto.payment;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class PaymentIdRequestDto {

    @NotNull
    private Long id;
}
