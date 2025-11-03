package com.springfood.api.openapi.controller;


import com.springfood.api.dto.payment.PaymentResponseDto;
import com.springfood.api.mapper.PaymentMapper;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.model.Payment;
import com.springfood.domain.service.PaymentService;
import com.springfood.domain.service.RestaurantPaymentService;
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
@Tag(name = "Restaurantes")
public interface RestaurantPaymentControllerDoc {

    @Operation(summary = "Lista pagamentos associados a um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Id do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    ResponseEntity<List<PaymentResponseDto>> findAll(@Parameter(description = "ID do restaurante", example = "1") Long id);

    @Operation(summary = "Associar um pagamento a um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Id do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    void attachPaymentToRestaurant(@Parameter(description = "ID do restaurante", example = "1") Long id,
                                   @Parameter(description = "ID do pagamento", example = "1") Long paymentId);

    @Operation(summary = "Desassociar um pagamento de um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Id do recurso inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada", content = @Content(schema = @Schema))
    })
    void detachPaymentToRestaurant(@Parameter(description = "ID do restaurante", example = "1") Long id,
                                   @Parameter(description = "ID do pagamento", example = "1") Long paymentId);
}
