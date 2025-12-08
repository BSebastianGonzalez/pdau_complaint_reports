package com.pdau.cr.listener;

import com.pdau.cr.config.RabbitConfig;
import com.pdau.cr.event.DenunciaCreadaEvent;
import com.pdau.cr.event.DenunciaEstadoActualizadoEvent;
import com.pdau.cr.model.ReporteDenuncia;
import com.pdau.cr.repository.ReporteDenunciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DenunciaEventsListener {

    private final ReporteDenunciaRepository repository;

    @RabbitListener(queues = RabbitConfig.DENUNCIA_QUEUE)
    public void onDenunciaCreada(DenunciaCreadaEvent event) {

        ReporteDenuncia r = new ReporteDenuncia();
        r.setDenunciaId(event.getDenunciaId());
        r.setTitulo(event.getTitulo());
        r.setFechaCreacion(event.getFechaCreacion());
        r.setArchivado(event.isArchivado());
        r.setEstado(event.getEstado());
        r.setCategorias(String.join(", ", event.getCategorias()));

        repository.save(r);
    }

    @RabbitListener(queues = RabbitConfig.ESTADO_QUEUE)
    public void onEstadoActualizado(DenunciaEstadoActualizadoEvent event) {

        repository.findById(event.getDenunciaId())
                .ifPresent(r -> {
                    r.setEstado(event.getNuevoEstado());
                    repository.save(r);
                });
    }
}
