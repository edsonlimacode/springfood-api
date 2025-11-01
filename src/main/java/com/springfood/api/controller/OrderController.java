package com.springfood.api.controller;


import com.springfood.api.dto.order.OrderRequestDto;
import com.springfood.api.dto.order.OrderResponseDto;
import com.springfood.api.dto.order.OrderResumeResponseDto;
import com.springfood.api.filter.OrderFilters;
import com.springfood.api.mapper.OrderMapper;
import com.springfood.api.openapi.controller.OrderControllerDoc;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.model.Order;
import com.springfood.domain.service.OrderService;
import com.springfood.infrastructure.especifications.OrderSpecs;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.springfood.core.data.PageableConverter.convertProperties;

@RestController
@RequestMapping("/orders")
public class OrderController implements OrderControllerDoc {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping
    public void create(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        Order order = this.orderMapper.toModel(orderRequestDto);

        this.orderService.create(order);
    }

    @CheckSecurity.Orders.canSearchAll
    @GetMapping
    public ResponseEntity<Page<OrderResumeResponseDto>> findAll(OrderFilters filters, Pageable pageable) {

        Pageable pageableConvert = convertPropertyToSort(pageable);

        Page<Order> orders = this.orderService.findAll(OrderSpecs.filters(filters), pageableConvert);

        List<OrderResumeResponseDto> orderResponseDtoList = this.orderMapper.toListResumeDto(orders.getContent());

        Page<OrderResumeResponseDto> orderResumeResponseDtoPage = new PageImpl<>(orderResponseDtoList, pageable, orders.getTotalElements());

        return ResponseEntity.ok(orderResumeResponseDtoPage);
    }

    @CheckSecurity.Orders.canSearch
    @GetMapping("/{code}")
    public ResponseEntity<OrderResponseDto> findByCode(@PathVariable String code) {

        Order order = this.orderService.findByCode(code);

        OrderResponseDto orderDto = this.orderMapper.toDto(order);

        return ResponseEntity.ok(orderDto);
    }

    /*
     * Converte as propriedades vindad dos parametros para a propriedade do modelo, para ser ordenada corretamente,
     * pois o modelMapper não consegue converter caso os nomes sejam diferentes.
     * ex: se tiver a propriedade do DTO -> "nameUser" será convertida para do model -> "user.name",isso pq o modelmapper
     * nao consegue saber que nameUser é user., já se for userName ele consegue então
     * esta abordagem é apenas para propriedades com nomes totalmente diferentes.
     * */
    private Pageable convertPropertyToSort(Pageable pageable) {
        var properties = Map.of(
                "nameUser", "user.name",
                "total", "total",
                "user.name","user.name");

        return convertProperties(pageable, properties);
    }


}
