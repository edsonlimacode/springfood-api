package com.springfood.api.controller;

import com.springfood.api.openapi.controller.OrderStatusControllerDoc;
import com.springfood.domain.service.OrderStatusService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/status")
public class OrderStatusController implements OrderStatusControllerDoc {

    @Autowired
    private OrderStatusService orderStatusService;

    @PutMapping("/{code}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Código do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    public void confirm(@PathVariable String code) {
        this.orderStatusService.confirm(code);
    }

    @PutMapping("/{code}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Código do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    public void cancel(@PathVariable String code) {
        this.orderStatusService.cancel(code);
    }

    @PutMapping("/{code}/deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Código do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    public void deliver(@PathVariable String code) {
        this.orderStatusService.deliver(code);
    }

}
