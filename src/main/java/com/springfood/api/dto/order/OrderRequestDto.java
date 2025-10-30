package com.springfood.api.dto.order;

import com.springfood.api.dto.address.AddressRequestDto;
import com.springfood.api.dto.orderItem.OrderItemRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {

    @NotNull
    private Long restaurantId;

    @NotNull
    private Long paymentId;

    @Valid
    @NotNull
    private AddressRequestDto address;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<OrderItemRequestDto> itens;
}
