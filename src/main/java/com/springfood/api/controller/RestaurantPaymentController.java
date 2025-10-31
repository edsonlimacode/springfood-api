package com.springfood.api.controller;


import com.springfood.api.dto.payment.PaymentResponseDto;
import com.springfood.api.mapper.PaymentMapper;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.model.Payment;
import com.springfood.domain.service.PaymentService;
import com.springfood.domain.service.RestaurantPaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/restaurants/{id}/payments")
@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public class RestaurantPaymentController {

    @Autowired
    private RestaurantPaymentService restaurantPaymentService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentMapper paymentMapper;

    @CheckSecurity.Restaurants.Admin
    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> findAll(@PathVariable Long id) {

        Set<Payment> payments = this.restaurantPaymentService.findPaymentsByRestaurantId(id);

        List<PaymentResponseDto> paymentResponseDtoList = this.paymentMapper.toListDto(payments);

        return ResponseEntity.ok(paymentResponseDtoList);
    }

    @CheckSecurity.Restaurants.Admin
    @PutMapping("/{paymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void attachPaymentToRestaurant(@PathVariable Long id, @PathVariable Long paymentId) {
        this.restaurantPaymentService.attachPaymentToRestaurant(id, paymentId);
    }

    @CheckSecurity.Restaurants.Admin
    @DeleteMapping("/{paymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void detachPaymentToRestaurant(@PathVariable Long id, @PathVariable Long paymentId) {
        this.restaurantPaymentService.detachPaymentToRestaurant(id, paymentId);
    }
}
