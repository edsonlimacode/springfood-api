package com.springfood.api.openapi.controller;

import com.springfood.api.dto.kitchen.KitchenResponseDto;
import com.springfood.api.openapi.anotation.PageableParameter;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface KitchenControllerDoc {

    @PageableParameter
    public Page<KitchenResponseDto> list(@Parameter(hidden = true) Pageable pageable);

}
