package com.springfood.api.openapi.controller;


import com.springfood.api.dto.order.OrderRequestDto;
import com.springfood.api.dto.order.OrderResponseDto;
import com.springfood.api.dto.order.OrderResumeResponseDto;
import com.springfood.api.filter.OrderFilters;
import com.springfood.api.openapi.anotation.PageableOrderParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface OrderControllerDoc {

    @Operation(summary = "Registra um pedido")
    void create(@RequestBody(description = "Representação do modelo de criação de um pedido") OrderRequestDto orderRequestDto);

    @Operation(summary = "Busca pedidos com filtros")
    @PageableOrderParameter
    @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(ref = "PageModelOrder")))
    ResponseEntity<Page<OrderResumeResponseDto>> findAll(@Parameter(hidden = true) OrderFilters filters, @Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Busca um pedido por código")
    ResponseEntity<OrderResponseDto> findByCode(String code);

}
