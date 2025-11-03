package com.springfood.api.dto.sale;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class DailySale {

//    private Date data;
    @Schema(example = "10")
    private Long quantity;

    @Schema(example = "1500.75")
    private BigDecimal total;

}
