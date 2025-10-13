package com.springfood.domain.interfaces;

import com.springfood.api.filter.DailySaleFilters;

public interface PDFDailySaleService {

    byte[] getPDFAllDailySales(DailySaleFilters filters, String timeZone);

}
