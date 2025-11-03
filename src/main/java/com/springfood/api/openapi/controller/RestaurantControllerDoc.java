package com.springfood.api.openapi.controller;

import com.springfood.api.dto.restaurant.RestaurantRequestDto;
import com.springfood.api.dto.restaurant.RestaurantResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestaurantControllerDoc {

    @Operation(summary = "Cadastrar um restaurante")
    public void create(@RequestBody(description = "Representação do recurso de um restaurante") RestaurantRequestDto restaurantRequestDto);

    @Operation(summary = "Atualizar um restaurante por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Id do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    public ResponseEntity<RestaurantResponseDto> update(@Parameter(description = "ID do restaurante", example = "1") Long id,
                                                        @RequestBody(description = "Representação do recurso de um restaurante")  RestaurantRequestDto restaurantRequestDto);

    @Operation(summary = "Listar todos os restaurantes")
    public ResponseEntity<List<RestaurantResponseDto>> listAll();

    @Operation(summary = "Buscar um restaurante por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Id do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    public ResponseEntity<RestaurantResponseDto> findOne(@Parameter(description = "ID do restaurante", example = "1") Long id);

    @Operation(summary = "Buscar restaurantes por nome e taxa de entrega")
    public ResponseEntity<List<RestaurantResponseDto>> findByNameAndDelivery(
            @Parameter(description = "Nome do restaurante", example = "nordestino") String name,
            @Parameter(description = "Valor inicial da entrega", example = "2.00") BigDecimal initialDeliveryValue,
            @Parameter(description = "Valor final da entrega", example = "10.50")BigDecimal endDeliveryValue);

    @Operation(summary = "Buscar restaurantes com entrega grátis")
    public ResponseEntity<List<RestaurantResponseDto>> findByDeliveryFree();

    @Operation(summary = "Ativar um restaurante por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Id da recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    public void active(@Parameter(description = "ID do restaurante", example = "1") Long id);

    @Operation(summary = "Inativar um restaurante por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Id da recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    public void inactive(@Parameter(description = "ID do restaurante", example = "1") Long id);

}
