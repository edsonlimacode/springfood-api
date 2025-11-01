package com.springfood.api.openapi.controller;


import com.springfood.api.dto.product.ProductRequestDto;
import com.springfood.api.dto.product.ProductResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Products", description = "Gerencia os produtos")
public interface ProductControllerDoc {

    @Operation(summary = "Cadastra um produto")
    void save(@RequestBody(description = "Representação do modelo de um novo produto") ProductRequestDto productRequestDto);

    @Operation(summary = "Atualiza um produto")
    ResponseEntity<ProductResponseDto> update(@Parameter(description = "ID do produto", example = "1") Long id,
                                              @RequestBody(description = "Representação do modelo de atualização de um produto") ProductRequestDto productRequestDto);

    @Operation(summary = "Busca um produto pelo ID do produto e ID do restaurante")
    ResponseEntity<ProductResponseDto> findByRestaurantId(Long id, Long restaurantId);

    @Operation(summary = "Lista todos os produtos de um restaurante")
    ResponseEntity<List<ProductResponseDto>> findAllByRestaurantId(Long id);

    @Operation(summary = "Deleta um produto por ID")
    void deleteById(Long id, Long restaurantId);
}
