package com.springfood.api.openapi.controller;

import com.springfood.api.dto.group.GroupResponseDto;
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
@Tag(name = "Usuários")
public interface GroupUserControllerDoc {

    @Operation(summary = "Lista os usuários associados a um grupo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Id do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema))
    })
    ResponseEntity<List<GroupResponseDto>> groupsByUserId(@Parameter(description = "ID do usuário", example = "1") Long id);

    @Operation(summary = "Associação de usuário com grupo")
    void attachGroupToUser(@Parameter(description = "ID do usuário", example = "1") Long id, @Parameter(description = "ID do grupo", example = "1") Long groupId);

    @Operation(summary = "Desassociação de usuário com grupo")
    void detachGroupToUser(@Parameter(description = "ID do usuário", example = "1") Long id, @Parameter(description = "ID do grupo", example = "1") Long groupId);

}

