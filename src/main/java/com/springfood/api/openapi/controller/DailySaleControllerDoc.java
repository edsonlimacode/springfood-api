package com.springfood.api.openapi.controller;


import com.springfood.api.dto.sale.DailySale;
import com.springfood.api.filter.DailySaleFilters;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.interfaces.DailySaleService;
import com.springfood.infrastructure.service.PDFDailySaleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@SecurityRequirement(name = "security_auth")
@Tag(name = "Relatórios", description = "Gerenciamento de relatórios")
public interface DailySaleControllerDoc {

    @Parameter(
            in = ParameterIn.QUERY,
            name = "restaurantId",
            description = "ID do restaurante, ex: 1",
            schema = @Schema(type = "integer", defaultValue = "1")
    )
    @Parameter(
            in = ParameterIn.QUERY,
            name = "startDate",
            description = "Data inicial para o filtro de vendas diárias, ex: 2024-01-01T00:00:00Z",
            schema = @Schema(type = "datetime", example = "2024-01-01T00:00:00Z")
    )
    @Parameter(
            in = ParameterIn.QUERY,
            name = "endDate",
            description = "Data final para o filtro de vendas diárias, ex: 2024-01-31T23:59:59Z",
            schema = @Schema(type = "datetime", example = "2024-01-01T00:00:00Z")
    )
    @Operation(summary = "Listar vendas diárias")
    List<DailySale> dailySales(@Parameter(hidden = true) DailySaleFilters filters,
                               @Parameter(description = "Timezone", example = "UTC") String timeZone);

    @Operation(summary = "Gerar relatório de vendas diárias em PDF")
    ResponseEntity<byte[]> pdfDailySales(@Parameter(hidden = true) DailySaleFilters filters,
                                         @Parameter(description = "Timezone", example = "UTC")  String timeZone);
}
