package utn.frc.bda.notificacion.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import utn.frc.bda.notificacion.dtos.PosicionDto;

@Service
public class PosicionDtoDeserializer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public PosicionDto deserializarPosicion(byte[] data) {
        try {
            // Convierte el Json en un objeto Java, en este caso posicionDto
            return objectMapper.readValue(data, PosicionDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize data", e);
        }
    }

    public PosicionDto deserializarPosicion(String jsonData) {
        try {
            // Convierte un String en un objeto
            return objectMapper.readValue(jsonData, PosicionDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize data", e);
        }
    }
}
