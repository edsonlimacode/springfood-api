package com.springfood.api.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Schema(name = "PageModelOrder")
@Getter
public class PageModelOrder extends PageModelOpenApi {

    @Schema(example = "[{\"id\":\"f9981ca4-5a5e-4da3-8f6f-22d1b3f2ef5b\",\"subtotal\":29.9,\"taxes\":2.9,\"totalValue\":32.8,\"status\":\"CONFIRMED\",\"creationDate\":\"2024-05-20T14:30:00Z\",\"restaurant\":{\"id\":1,\"name\":\"Restaurante A\"},\"client\":{\"id\":1,\"name\":\"João Silva\"}}]")
    private List<Object> data;
}
