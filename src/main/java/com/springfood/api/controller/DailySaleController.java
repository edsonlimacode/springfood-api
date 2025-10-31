package com.springfood.api.controller;


import com.springfood.api.dto.sale.DailySale;
import com.springfood.api.filter.DailySaleFilters;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.interfaces.DailySaleService;
import com.springfood.infrastructure.service.PDFDailySaleServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
public class DailySaleController {

    @Autowired
    private DailySaleService dailySaleService;

    @Autowired
    private PDFDailySaleServiceImpl pdfDailySaleService;

    @CheckSecurity.Report.Admin
    @GetMapping(value = "/daily", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<DailySale> dailySales(DailySaleFilters filters,
                                      @RequestParam(required = false, defaultValue = "UTC") String timeZone) {
        return dailySaleService.getAllDailySales(filters, timeZone);
    }

    @CheckSecurity.Report.Admin
    @GetMapping(value = "/daily/pdf", produces = {MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<byte[]> pdfDailySales(DailySaleFilters filters,
                                                @RequestParam(required = false, defaultValue = "UTC") String timeZone) {

        byte[] pdfAllDailySales = pdfDailySaleService.getPDFAllDailySales(filters, timeZone);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfAllDailySales);
    }
}
