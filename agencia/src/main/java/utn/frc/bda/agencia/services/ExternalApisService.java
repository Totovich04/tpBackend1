package utn.frc.bda.agencia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utn.frc.bda.agencia.dtos.PosicionDto;
import utn.frc.bda.agencia.dtos.externos.NotificacionDto;


import java.time.LocalDateTime;
import java.util.Collections;


@Service
public class ExternalApisService {

    private final RestTemplate restTemplate;
    private final String notificacionUrl = "http://localhost:8080/notificaciones";

    @Autowired
    public ExternalApisService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void enviarMensajeRadioExcedido(PosicionDto posicion) {
        NotificacionDto notificacionDto = new NotificacionDto(
                null, LocalDateTime.now(), "El vehiculo excedio el radio permitido"+ posicion);
    notificacionDto.setReciverEmails(Collections.emptyList());
    enviarNotficacion(notificacionDto);
    }

    public void enviarMensajeZonaPeligrosa(PosicionDto posicion){
        NotificacionDto notificacionDto = new NotificacionDto(
                null, LocalDateTime.now(), "El vehiculo ingreso a una zona peligrosa"+ posicion);
        notificacionDto.setReciverEmails(Collections.emptyList());
        enviarNotficacion(notificacionDto);
    }

    private void enviarNotficacion(NotificacionDto notificacionDto){
        restTemplate.postForObject(notificacionUrl, notificacionDto, NotificacionDto.class);
    }
}
