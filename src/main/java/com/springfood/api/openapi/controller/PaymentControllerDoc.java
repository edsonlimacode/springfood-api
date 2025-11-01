package com.springfood.api.openapi.controller;


import com.springfood.api.dto.payment.PaymentRequestDto;
import com.springfood.api.dto.payment.PaymentResponseDto;
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
@Tag(name = "Formas de Pagamento")
public interface PaymentControllerDoc {


    @Operation(summary = "Cadastra uma forma de pagamento")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Cadastrado")})
    void save(@RequestBody(description = "Representação do modelo de um novo pagamento") PaymentRequestDto paymentRequestDto);

    @Operation(summary = "Atualiza uma forma de pagamento")
     ResponseEntity<PaymentResponseDto> update(@Parameter(name = "ID de um pagamento", example = "1") Long id,
                                                     @RequestBody(description = "Representação do modelo de atualizar um pagamento")
                                                     PaymentRequestDto paymentRequestDto);

    @Operation(summary = "Lista as formas de pagamentos")
     ResponseEntity<List<PaymentResponseDto>> list();

    @Operation(summary = "busca uma forma de pagamento pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Id do pagamento inválido", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não econtrado", content = @Content(schema = @Schema))
    })
    public ResponseEntity<PaymentResponseDto> findOne(@Parameter(description = "ID de um pagamento", example = "1") Long id);

    @Operation(summary = "remove uma forma de pagamento pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Nenhum conteudo disponivel"),
            @ApiResponse(responseCode = "400", description = "Id da pagamento inválido", content = @Content(schema = @Schema(ref = "ProblemDetails"))),
            @ApiResponse(responseCode = "404", description = "Pagamento não econtrado", content = @Content(schema = @Schema(ref = "ProblemDetails")))
    })
    public void deleteById(@Parameter(description = "ID de um pagamento", example = "1", required = true) Long id);

}
