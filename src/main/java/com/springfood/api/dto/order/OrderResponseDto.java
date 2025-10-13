package com.springfood.api.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springfood.api.dto.address.AddressResponseDto;
import com.springfood.api.dto.orderItem.OrderItemResponseDto;
import com.springfood.api.dto.payment.PaymentResponseDto;
import com.springfood.api.dto.restaurant.RestaurantResponseDto;
import com.springfood.api.dto.user.UserResponseDto;
import com.springfood.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {

    private String code;

    private BigDecimal total;

    @JsonProperty("sub_total")
    private BigDecimal subTotal;

    private BigDecimal delivery;

    private OrderStatus status;

    private AddressResponseDto address;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    @JsonProperty("confirmation_date")
    private OffsetDateTime confirmationDate;

    @JsonProperty("delivery_date")
    private OffsetDateTime deliveryDate;

    @JsonProperty("cancel_date")
    private OffsetDateTime cancelDate;

    private RestaurantResponseDto restaurant;

    private PaymentResponseDto payment;

    private UserResponseDto user;

    private List<OrderItemResponseDto> itens;


}
