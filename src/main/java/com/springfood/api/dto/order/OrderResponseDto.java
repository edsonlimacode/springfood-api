package com.springfood.api.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springfood.api.dto.address.AddressResponseDto;
import com.springfood.api.dto.orderItem.OrderItemResponseDto;
import com.springfood.api.dto.payment.PaymentResponseDto;
import com.springfood.api.dto.restaurant.RestaurantResponseDto;
import com.springfood.api.dto.user.UserResponseDto;
import com.springfood.domain.model.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {

    @Schema(example = "f9981ca4-5a5e-4da3-8f6f-22d1b3f2ef5b")
    private String code;

    @Schema(example = "32.80")
    private BigDecimal total;

    @Schema(example = "44.90")
    @JsonProperty("sub_total")
    private BigDecimal subTotal;

    @Schema(example = "12.10")
    private BigDecimal delivery;

    @Schema(example = "CONFIRMED")
    private OrderStatus status;

    private AddressResponseDto address;

    @Schema(example = "2024-05-20T14:30:00Z")
    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    @Schema(example = "2024-05-20T15:00:00Z")
    @JsonProperty("confirmation_date")
    private OffsetDateTime confirmationDate;

    @Schema(example = "2024-05-20T15:30:00Z")
    @JsonProperty("delivery_date")
    private OffsetDateTime deliveryDate;

    @Schema(example = "2024-05-20T16:00:00Z")
    @JsonProperty("cancel_date")
    private OffsetDateTime cancelDate;

    private RestaurantResponseDto restaurant;

    private PaymentResponseDto payment;

    private UserResponseDto user;

    private List<OrderItemResponseDto> itens;


}
