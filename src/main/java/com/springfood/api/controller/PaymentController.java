package com.springfood.api.controller;


import com.springfood.api.openapi.controller.PaymentControllerDoc;
import com.springfood.api.dto.payment.PaymentRequestDto;
import com.springfood.api.dto.payment.PaymentResponseDto;
import com.springfood.api.mapper.payment.PaymentMapper;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.model.Payment;
import com.springfood.domain.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payments")
public class PaymentController implements PaymentControllerDoc {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentMapper mapper;

    @CheckSecurity.Master
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @Valid @RequestBody PaymentRequestDto paymentRequestDto) {

        Payment payment = this.mapper.toModel(paymentRequestDto);

        this.paymentService.create(payment);
    }

    @CheckSecurity.Master
    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> update(@PathVariable Long id,
                                                     @Valid @RequestBody PaymentRequestDto paymentRequestDto) {

        Payment payment = this.mapper.toModel(paymentRequestDto);

        Payment paymentUpdated = this.paymentService.update(id, payment);

        PaymentResponseDto paymentResponseDto = this.mapper.toDto(paymentUpdated);

        return ResponseEntity.ok(paymentResponseDto);
    }

    @CheckSecurity.AdminAndMaster
    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> list() {
        List<Payment> payments = this.paymentService.listAll();

        List<PaymentResponseDto> paymentResponseDtoList = this.mapper.toListDto(payments);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(paymentResponseDtoList);
    }

    @CheckSecurity.AdminAndMaster
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> findOne(@PathVariable Long id) {
        Payment payment = this.paymentService.findById(id);

        PaymentResponseDto paymentResponseDto = this.mapper.toDto(payment);

        return ResponseEntity.ok(paymentResponseDto);
    }

    @CheckSecurity.Master
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.paymentService.delete(id);
    }

}
