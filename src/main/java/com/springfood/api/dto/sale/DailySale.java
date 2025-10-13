package com.springfood.api.dto.sale;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class DailySale {

//    private Date data;
    private Long quantity;
    private BigDecimal total;

}
