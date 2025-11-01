package com.springfood.api.openapi.controller;

import com.springfood.api.dto.user.UserCreateRequestDto;
import com.springfood.api.dto.user.UserResponseDto;
import com.springfood.api.dto.user.UserUpdatePasswordRequestDto;
import com.springfood.api.dto.user.UserUpdateRequestDto;
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

import java.util.List;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuários")
public interface UserControllerDoc {

    @Operation(summary = "Cadastra um novo usuário")
    void save(@RequestBody(description = "Representação do modelo de um novo usuário") UserCreateRequestDto userCreateRequestDto);

    @Operation(summary = "Atualiza um usuário")
    ResponseEntity<UserResponseDto> update(@Parameter(description = "ID do usuário ", example = "1") Long id,
                                           @RequestBody(description = "Representação do modelo de atualização de um usuário") UserUpdateRequestDto userUpdateRequestDto);

    @Operation(summary = "Atualiza a senha de um usuário")
    void updatePassword(@Parameter(description = "ID do usuário", example = "1") Long id, @RequestBody UserUpdatePasswordRequestDto passwordRequestDto);

    @Operation(summary = "Lista os usuários")
    ResponseEntity<List<UserResponseDto>> list();

    @Operation(summary = "Busca um usuário pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Id do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema))
    })
    ResponseEntity<UserResponseDto> findById(@Parameter(description = "ID do usuário", example = "1") Long id);

    @Operation(summary = "Remove um usuário pelo ID")
    void deleteById(@Parameter(description = "ID do usuário", example = "1") Long id);
}
