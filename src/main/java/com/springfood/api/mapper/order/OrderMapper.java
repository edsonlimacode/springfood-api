package com.springfood.api.mapper.order;

import com.springfood.api.dto.order.OrderRequestDto;
import com.springfood.api.dto.order.OrderResponseDto;
import com.springfood.api.dto.order.OrderResumeResponseDto;
import com.springfood.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private ModelMapper mapper;

    public Order toModel(OrderRequestDto orderRequestDto) {
        return mapper.map(orderRequestDto, Order.class);
    }

    public OrderResponseDto toDto(Order order) {
        return mapper.map(order, OrderResponseDto.class);
    }

    public OrderResumeResponseDto toResumeDto(Order order) {
        return mapper.map(order, OrderResumeResponseDto.class);
    }

    public List<OrderResponseDto> toListDto(List<Order> orders) {
        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<OrderResumeResponseDto> toListResumeDto(List<Order> orders) {
        return orders.stream().map(this::toResumeDto).collect(Collectors.toList());
    }

}
