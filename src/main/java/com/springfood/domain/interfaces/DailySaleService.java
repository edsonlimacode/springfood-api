package com.springfood.domain.interfaces;

import com.springfood.api.dto.sale.DailySale;
import com.springfood.api.filter.DailySaleFilters;

import java.util.List;

public interface DailySaleService {

    List<DailySale> getAllDailySales(DailySaleFilters filters, String timeZone);

}
