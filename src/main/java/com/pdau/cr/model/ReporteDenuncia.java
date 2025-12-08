package com.pdau.cr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDenuncia {

    @Id
    private Long denunciaId;

    @Column(columnDefinition = "VARCHAR(255)")
    private String titulo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean archivado;

    @Column(columnDefinition = "VARCHAR(100)")
    private String estado;

    @Column(columnDefinition = "TEXT")
    private String categorias;
}
