package com.pdau.cr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String titulo;
    private Date fechaCreacion;
    private boolean archivado;
    private String estado;
    private String categorias;
}
