package utn.frc.bda.agencia.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utn.frc.bda.agencia.dtos.externos.NotificacionRadioExcedidoDto;
import utn.frc.bda.agencia.dtos.externos.RestriccionesDto;

import java.util.Arrays;
import java.util.List;

@Service
public class RestriccionesService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${agencia.restricciones.url}")
    private String url;

    @Value("${notificacion.notificaciones.url}")
    private String urlNoti;

    @Cacheable("restrictionsApiCache")
    public RestriccionesDto getRestricciones(){
        return restTemplate.getForObject(url, RestriccionesDto.class);
    }

    public List<NotificacionRadioExcedidoDto> getNotificacionesRadioExcedido(){
        NotificacionRadioExcedidoDto[] notiArray = restTemplate.getForObject(urlNoti+"/notificaciones/seguridad/radio-excedido", NotificacionRadioExcedidoDto[].class);

        assert notiArray != null;
        return Arrays.asList(notiArray);
    }
}
