package com.pdau.cr.repository;

import com.pdau.cr.model.ReporteDenuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReporteDenunciaRepository extends JpaRepository<ReporteDenuncia, Long> {
    // TOTAL
    @Query("SELECT COUNT(r) FROM ReporteDenuncia r")
    long totalDenuncias();

    // POR ESTADO
    @Query("SELECT r.estado, COUNT(r) FROM ReporteDenuncia r GROUP BY r.estado")
    List<Object[]> totalPorEstado();

    // POR FECHAS
    @Query("SELECT COUNT(r) FROM ReporteDenuncia r WHERE r.fechaCreacion BETWEEN :inicio AND :fin")
    long totalPorFechas(@Param("inicio") Date inicio,
                        @Param("fin") Date fin);

    // Todas para categorías
    List<ReporteDenuncia> findAll();
}
