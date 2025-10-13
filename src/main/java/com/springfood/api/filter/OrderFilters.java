package com.springfood.api.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderFilters {

    private Long userId;

    private Long restaurantId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) //evita o erro ao manda a data como string
    private OffsetDateTime endDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime startDate;

}
