package com.springfood.api.dto.order;

import com.springfood.api.dto.address.AddressRequestDto;
import com.springfood.api.dto.orderItem.OrderItemRequestDto;
import com.springfood.api.dto.payment.PaymentIdRequestDto;
import com.springfood.api.dto.restaurant.RestaurantIdRequestDto;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {

    @Valid
    @NotNull
    private RestaurantIdRequestDto restaurant;

    @Valid
    @NotNull
    private PaymentIdRequestDto payment;

    @Valid
    @NotNull
    private AddressRequestDto address;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<OrderItemRequestDto> itens;
}
