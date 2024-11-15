package utn.frc.bda.notificacion.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public abstract class NotificacionDto {
    private Integer id;
    private List<String> emails;
    private LocalDateTime fechaNotificacion;
    private String texto;

    // Constructor con parámetros para inicializar los campos id, fechaNotificacion y texto
    public NotificacionDto(Integer id, LocalDateTime fechaNotificacion, String texto) {
        this.id = id;
        this.fechaNotificacion = fechaNotificacion;
        this.texto = texto;
    }
}