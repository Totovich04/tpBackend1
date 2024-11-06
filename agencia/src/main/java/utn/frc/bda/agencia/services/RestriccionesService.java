package utn.frc.bda.agencia.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utn.frc.bda.agencia.dtos.externos.RestriccionesDto;

@Service
public class RestriccionesService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${agencia.restricciones.url}")
    private String url;

    @Cacheable("restrictionsApiCache")
    public RestriccionesDto getRestricciones(){
        return restTemplate.getForObject(url, RestriccionesDto.class);
    }
}
