package com.springfood.api.openapi.anotation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(
        in = ParameterIn.QUERY,
        name = "page",
        description = "Número da página (0..N)",
        schema = @Schema(type = "integer", defaultValue = "0")
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "size",
        description = "Quantidade de elementos por página",
        schema = @Schema(type = "integer", defaultValue = "10")
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "sort",
        description = "Critérios de ordenação ex: total,asc ou então total,desc). " +
                "Múltiplos critérios de ordenação podem ser informados.",
        examples = {
                @ExampleObject("total"),
                @ExampleObject("total,asc"),
                @ExampleObject("total,desc")
        }
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "userId",
        description = "Id do cliente",
        schema = @Schema(type = "integer")
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "restaurantId",
        description = "Id do restaurante",
        schema = @Schema(type = "integer")
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "startDate",
        description = "Data/hora de criação inicial do pedido, ex: 2025-09-01T00:00:31z",
        schema = @Schema(type = "string", format = "date-time")
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "endDate",
        description = "Data/hora de criação final do pedido, ex: 2025-09-15T23:59:59z",
        schema = @Schema(type = "string", format = "date-time")
)
public @interface PageableOrderParameter {
}
