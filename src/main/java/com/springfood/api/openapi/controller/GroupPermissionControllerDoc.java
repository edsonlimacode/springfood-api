package com.springfood.api.openapi.controller;


import com.springfood.api.dto.permission.PermissionResponseDto;
import com.springfood.api.mapper.PermissionMapper;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.model.Permission;
import com.springfood.domain.service.GroupPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GroupPermissionControllerDoc {

    @Operation(summary = "Lista as permissões associadas a um grupo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "ID do grupo inválido",content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema()))
    })
    ResponseEntity<List<PermissionResponseDto>> findAll(@Parameter(description = "ID do grupo", example = "1") Long id);

    @Operation(summary = "Associação de permissão com grupo")
    void attachPermissionToRestaurant(@Parameter(description = "ID do grupo", example = "1") Long id, @Parameter(description = "ID da permissão", example = "1") Long permissionId);

    @Operation(summary = "Desassociação de permissão com grupo")
    void detachPermissionToRestaurant(@Parameter(description = "ID do grupo", example = "1") Long id, @Parameter(description = "ID da permissão", example = "1") Long permissionId);
}
