package utn.frc.bda.notificacion.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NotificacionDto {
    private Integer id;
    private LocalDateTime fechaNotificacion;
    private String mensaje;

    public NotificacionDto(Integer id, LocalDateTime fechaNotificacion, String mensaje) {
        this.id = id;
        this.fechaNotificacion = fechaNotificacion;
        this.mensaje = mensaje;
    }

}
