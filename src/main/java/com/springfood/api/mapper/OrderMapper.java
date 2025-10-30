package com.springfood.api.mapper;

import com.springfood.api.dto.order.OrderRequestDto;
import com.springfood.api.dto.order.OrderResponseDto;
import com.springfood.api.dto.order.OrderResumeResponseDto;
import com.springfood.api.dto.orderItem.OrderItemRequestDto;
import com.springfood.domain.model.Order;
import com.springfood.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "productId", target = "product.id")
    OrderItem toModel(OrderItemRequestDto orderItemRequestDto);

    List<OrderItem> toModelList(List<OrderItemRequestDto> orderItemRequestDtoList);

    @Mapping(source = "itens", target = "itens")  // Mapeia a lista de OrderItemRequestDto para List<OrderItem>
    @Mapping(target = "address.city.id", source = "address.cityId")
    @Mapping(target = "restaurant.id", source = "restaurantId")
    @Mapping(target = "payment.id", source = "paymentId")
    Order toModel(OrderRequestDto orderRequestDto);

    @Mapping(target = "address.city", source = "address.city.name")
    @Mapping(target = "address.state", source = "address.city.state.name")
    @Mapping(target = "restaurant.address.city", source = "address.city.name")
    @Mapping(target = "restaurant.address.state", source = "address.city.state.name")
    OrderResponseDto toDto(Order order);

    OrderResumeResponseDto toResumeDto(Order order);

    List<OrderResponseDto> toListDto(List<Order> orders);

    List<OrderResumeResponseDto> toListResumeDto(List<Order> orders);

}
