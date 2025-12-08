package com.pdau.cr.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaEstadoActualizadoEvent {

    private Long denunciaId;
    private String nuevoEstado;
}
