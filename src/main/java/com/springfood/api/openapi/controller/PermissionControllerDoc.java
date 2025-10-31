package com.springfood.api.openapi.controller;


import com.springfood.api.dto.permission.PermissionRequestDto;
import com.springfood.api.dto.permission.PermissionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Permissões")
public interface PermissionControllerDoc {

    @Operation(summary = "Cadastra uma permissão")
    void save(@RequestBody(description = "Representação do modelo de uma permissão") PermissionRequestDto permissionRequestDto);

    @Operation(summary = "Atualiza uma permissão por ID")
    ResponseEntity<PermissionResponseDto> update(@Parameter(description = "ID da permissão",example = "1") Long id,
                                                 @RequestBody(description = "Representação do modelo de atualização da permissão") PermissionRequestDto permissionRequestDto);

    @Operation(summary = "Lista todas as permissões")
    ResponseEntity<List<PermissionResponseDto>> list();

    @Operation(summary = "Busca uma permissão por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "ID do grupo inválido"),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado")
    })
    ResponseEntity<PermissionResponseDto> findOne(@Parameter(description = "ID da permissão",example = "1") Long id);

    @Operation(summary = "Deleta uma permissão por ID")
    void deleteById(@Parameter(description = "ID da permissão",example = "1")Long id);

}
