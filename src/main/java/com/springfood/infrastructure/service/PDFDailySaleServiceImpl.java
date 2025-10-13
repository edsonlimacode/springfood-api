package com.springfood.infrastructure.service;

import com.springfood.api.dto.sale.DailySale;
import com.springfood.api.filter.DailySaleFilters;
import com.springfood.domain.interfaces.DailySaleService;
import com.springfood.domain.interfaces.PDFDailySaleService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Service
public class PDFDailySaleServiceImpl implements PDFDailySaleService {

    @Autowired
    private DailySaleService dailySaleService;

    @Override
    public byte[] getPDFAllDailySales(DailySaleFilters filters, String timeZone) {
        try {
            InputStream resource = getClass().getResourceAsStream("/reports/daily-sales.jasper");

            List<DailySale> allDailySales = this.dailySaleService.getAllDailySales(filters, timeZone);

            var dataSource = new JRBeanCollectionDataSource(allDailySales);

            var parameters = new HashMap<String, Object>();
            parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

            JasperPrint filledReport = JasperFillManager.fillReport(resource, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(filledReport);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
