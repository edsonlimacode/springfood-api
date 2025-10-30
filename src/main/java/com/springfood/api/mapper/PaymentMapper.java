package com.springfood.api.mapper;

import com.springfood.api.dto.payment.PaymentRequestDto;
import com.springfood.api.dto.payment.PaymentResponseDto;
import com.springfood.domain.model.Payment;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment toModel(PaymentRequestDto paymentResponseDto);

    PaymentResponseDto toDto(Payment payment);

    List<PaymentResponseDto> toListDto(Collection<Payment> payments);

}
