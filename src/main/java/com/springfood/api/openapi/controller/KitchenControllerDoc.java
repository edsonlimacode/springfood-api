package com.springfood.api.openapi.controller;

import com.springfood.api.dto.kitchen.KitchenRequestDto;
import com.springfood.api.dto.kitchen.KitchenResponseDto;
import com.springfood.api.openapi.anotation.PageableKitchenParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
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
@Tag(name = "Cozinhas", description = "Gerencia as cozinhas")
public interface KitchenControllerDoc {

    @PageableKitchenParameter
    @Operation(summary = "Lista as cozinhas")
    @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(ref = "PageModelKitchen")))
    Page<KitchenResponseDto> list(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Cadastra uma nova cozinha")
    void save(@RequestBody(description = "Representação do modelo de uma nova cozinha") KitchenRequestDto kitchenRequestDto);

    @Operation(summary = "Atualiza uma cozinha por ID")
    ResponseEntity<KitchenResponseDto> update(@Parameter(description = "ID de uma cozinha",example = "1") Long id,
                                              @RequestBody(description = "Representação do modelo de atualização de uma cozinha") KitchenRequestDto kitchenRequestDto);

    @Operation(summary = "Busca uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Id da recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    ResponseEntity<KitchenResponseDto> findOne( Long id);

    @Operation(summary = "Exclui uma cozinha por ID")
    void deleteById(@Parameter(description = "ID de uma cozinha", example = "1") Long id);

}
