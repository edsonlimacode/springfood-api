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
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface OrderControllerDoc {

    @Operation(summary = "Registra um novo pedido")
    void create(@RequestBody(description = "Representação do modelo de criação de um pedido") OrderRequestDto orderRequestDto);

    @Operation(summary = "Busca pedidos com filtros")
    @PageableOrderParameter
    @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(ref = "PageModelOrder")))
    ResponseEntity<Page<OrderResumeResponseDto>> findAll(@Parameter(hidden = true) OrderFilters filters, @Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Busca um pedido por código")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Código do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    ResponseEntity<OrderResponseDto> findByCode(@Parameter(description = "Código do recurso") String code);

}
