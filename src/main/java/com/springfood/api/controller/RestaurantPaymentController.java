package com.springfood.api.controller;


import com.springfood.api.dto.payment.PaymentResponseDto;
import com.springfood.api.mapper.payment.PaymentMapper;
import com.springfood.domain.model.Payment;
import com.springfood.domain.service.PaymentService;
import com.springfood.domain.service.RestaurantPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/restaurants/{id}/payments")
public class RestaurantPaymentController {

    @Autowired
    private RestaurantPaymentService restaurantPaymentService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentMapper paymentMapper;

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> findAll(@PathVariable Long id) {

        Set<Payment> payments = this.restaurantPaymentService.findPaymentsByRestaurantId(id);

        List<PaymentResponseDto> paymentResponseDtoList = this.paymentMapper.toListDto(payments);

        return ResponseEntity.ok(paymentResponseDtoList);
    }

    @PutMapping("/{paymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bindPaymentToRestaurant(@PathVariable Long id, @PathVariable Long paymentId) {
        this.restaurantPaymentService.bindPaymentToRestaurant(id, paymentId);
    }

    @DeleteMapping("/{paymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unbindPaymentToRestaurant(@PathVariable Long id, @PathVariable Long paymentId) {
        this.restaurantPaymentService.detachPaymentToRestaurant(id, paymentId);
    }
}
