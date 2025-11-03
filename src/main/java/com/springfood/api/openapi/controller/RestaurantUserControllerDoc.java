package com.springfood.api.openapi.controller;


import com.springfood.api.dto.user.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestaurantUserControllerDoc {

    @Operation(summary = "Listar usuários associados a um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Id do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    public ResponseEntity<List<UserResponseDto>> findAll(@Parameter(description = "ID do restaurante", example = "1") Long id);

    @Operation(summary = "Associar um usuário a um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Id do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    public void attachUserToRestaurant(@Parameter(description = "ID do restaurante", example = "1") Long id,
                                       @Parameter(description = "ID do usuário", example = "1") Long userId);

    @Operation(summary = "Desassociar um usuário de um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Id do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    public void detachedUserToRestaurant(@Parameter(description = "ID do restaurante", example = "1") Long id,
                                         @Parameter(description = "ID do usuário", example = "1") Long userId);
}
