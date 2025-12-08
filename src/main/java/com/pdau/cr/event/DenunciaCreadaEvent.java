package com.pdau.cr.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DenunciaCreadaEvent {
    private Long denunciaId;
    private String titulo;
    private Date fechaCreacion;
    private boolean archivado;
    private String estado;
    private List<String> categorias;
}
