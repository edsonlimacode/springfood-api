package com.springfood.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@Schema(name = "ProblemDetails")
public class ProblemDetails {

    @Schema(example = "200")
    private Integer status;
    @Schema(example = "https://foodapi/dados-inválidos")
    private String type;
    @Schema(example = "Dados inválidos")
    private String title;
    @Schema(example = "Um ou mais campos estão incorretos")
    private String detail;
    @Schema(example = "Verifique os dados, e tente novamente")
    private String userMessage;
    @Schema(example = "2025-10-09H21:1512Z")
    private LocalDateTime timestamp;
    @Schema(example = "Campos")
    private List<Field> fields;

    @Getter
    @Builder
    @Schema(name = "Field")
    public static class Field {
        @Schema(example = "preço")
        private String field;
        @Schema(example = "Campo preço é obrigatório")
        private String message;
    }
}
