package com.springfood.api.openapi.controller;

import com.springfood.domain.service.OrderStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface OrderStatusControllerDoc {

    @Operation(summary = "Confirma um pedido")
    public void confirm(@Parameter(description = "Código do pedido", example = "b3d25e81-cdb2-4260-bb78-144df0f67157") String code);

    @Operation(summary = "Cancela um pedido")
    public void cancel(@Parameter(description = "Código do pedido", example = "b3d25e81-cdb2-4260-bb78-144df0f67157")  String code);

    @Operation(summary = "Entrega um pedido")
    public void deliver(@Parameter(description = "Código do pedido", example = "b3d25e81-cdb2-4260-bb78-144df0f67157")  String code);

}
