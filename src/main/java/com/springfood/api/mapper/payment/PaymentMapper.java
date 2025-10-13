package com.springfood.api.mapper.payment;

import com.springfood.api.dto.payment.PaymentRequestDto;
import com.springfood.api.dto.payment.PaymentResponseDto;
import com.springfood.domain.model.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {

    @Autowired
    private ModelMapper mapper;

    public Payment toModel(PaymentRequestDto paymentResponseDto) {
        return mapper.map(paymentResponseDto, Payment.class);
    }

    public PaymentResponseDto toDto(Payment payment) {
        return mapper.map(payment, PaymentResponseDto.class);
    }

    public List<PaymentResponseDto> toListDto(Collection<Payment> payments) {
        return payments.stream().map(this::toDto).collect(Collectors.toList());
    }

}
