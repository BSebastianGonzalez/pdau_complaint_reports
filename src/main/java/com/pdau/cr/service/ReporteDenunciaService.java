package com.pdau.cr.service;

import com.pdau.cr.model.ReporteDenuncia;
import com.pdau.cr.repository.ReporteDenunciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReporteDenunciaService {

    private final ReporteDenunciaRepository repository;

    // Definimos las categorías estándar
    private static final List<String> CATEGORIAS_OFICIALES = List.of(
            "Acoso",
            "Violencia",
            "Discriminación",
            "Plagio o fraude académico",
            "Maltrato o abuso de poder por docentes",
            "Corrupción institucional",
            "Problemas con personal de seguridad",
            "Condiciones deficientes en instalaciones",
            "Negligencia en salud o atención psicológica",
            "Problemas con servicios universitarios",
            "Consumo o venta de sustancias",
            "Grupos estudiantiles o actividades",
            "Robo o suplantación",
            "Otro"
    );

    // Estados
    private static final List<String> ESTADOS = List.of(
            "En revisión",
            "Resuelta"
    );

    public long obtenerTotal() {
        return repository.totalDenuncias();
    }

    public Map<String, Long> obtenerPorEstado() {
        Map<String, Long> resultado = new LinkedHashMap<>();

        // Inicializar en 0
        for (String e : ESTADOS) {
            resultado.put(e, 0L);
        }

        var datos = repository.totalPorEstado();

        for (Object[] fila : datos) {
            String estado = (String) fila[0];
            Long total = (Long) fila[1];
            resultado.put(estado, total);
        }

        return resultado;
    }

    public long obtenerPorRangoFechas(Date inicio, Date fin) {
        return repository.totalPorFechas(inicio, fin);
    }

    public Map<String, Long> obtenerPorCategoria() {
        Map<String, Long> resultado = new LinkedHashMap<>();

        // Inicializar en 0
        for (String c : CATEGORIAS_OFICIALES) {
            resultado.put(c, 0L);
        }

        List<ReporteDenuncia> denuncias = repository.findAll();

        for (ReporteDenuncia d : denuncias) {
            if (d.getCategorias() == null) continue;

            String[] cats = d.getCategorias().split(",\\s*");

            for (String cat : cats) {
                if (resultado.containsKey(cat)) {
                    resultado.put(cat, resultado.get(cat) + 1);
                } else {
                    resultado.put(cat, 1L); // para categorías inesperadas
                }
            }
        }

        return resultado;
    }
}

