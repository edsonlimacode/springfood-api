package com.springfood.api.openapi.controller;

import com.springfood.api.dto.group.GroupRequestDto;
import com.springfood.api.dto.group.GroupResponseDto;
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
@Tag(name = "Grupos")
public interface GroupControllerDoc {

    @Operation(summary = "Cadastra um grupo")
    void save(@RequestBody(description = "Representação do modelo de um novo grupo") GroupRequestDto groupRequestDto);

    @Operation(summary = "Atualiza um grupo por ID")
    ResponseEntity<GroupResponseDto> update(@Parameter(description = "ID de um grupo", example = "1") Long id,
                                            @RequestBody(description = "Representação do modelo de atualização de um grupo") GroupRequestDto groupRequestDto);

    @Operation(summary = "Lista todos os grupos")
    ResponseEntity<List<GroupResponseDto>> list();

    @Operation(summary = "Busca um grupo por ID")
    @ApiResponses({
                    @ApiResponse(responseCode = "200", description = "Sucesso"),
                    @ApiResponse(responseCode = "400", description = "ID do grupo inválido"),
                    @ApiResponse(responseCode = "404", description = "Grupo não encontrado")
            })
    ResponseEntity<GroupResponseDto> findOne(@Parameter(description = "ID de um grupo", example = "1") Long id);

    @Operation(summary = "Deleta um grupo por ID")
    void deleteById(@Parameter(description = "ID de um grupo", example = "1") Long id);

}
