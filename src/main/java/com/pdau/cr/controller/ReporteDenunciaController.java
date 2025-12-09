package com.pdau.cr.controller;

import com.pdau.cr.service.ReporteDenunciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes/denuncias")
@RequiredArgsConstructor
public class ReporteDenunciaController {

    private final ReporteDenunciaService service;

    @GetMapping("/total")
    public long total() {
        return service.obtenerTotal();
    }

    @GetMapping("/por-estado")
    public Map<String, Long> porEstado() {
        return service.obtenerPorEstado();
    }

    @GetMapping("/por-categoria")
    public Map<String, Long> porCategoria() {
        return service.obtenerPorCategoria();
    }

    @GetMapping("/por-fechas")
    public long porFechas(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin
    ) {
        Date desde = java.sql.Date.valueOf(inicio);
        Date hasta = java.sql.Date.valueOf(fin);
        return service.obtenerPorRangoFechas(desde, hasta);
    }
}

