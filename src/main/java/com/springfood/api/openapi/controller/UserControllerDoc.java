package com.springfood.api.openapi.controller;

import com.springfood.api.dto.user.UserCreateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface UserControllerDoc {


    @Operation(summary = "Cadastra um novo usuário")
    public void save(@RequestBody(description = "Representação do modelo de um novo usuário") UserCreateRequestDto userCreateRequestDto);

}
