package utn.frc.bda.notificacion.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificacionDto {
    private Integer id;
    private LocalDateTime fechaNotificacion;
    private String mensaje;

    public NotificacionDto(Integer id, LocalDateTime fechaNotificacion, String mensaje) {
        this.id = id;
        this.fechaNotificacion = fechaNotificacion;
        this.mensaje = mensaje;
    }

    public NotificacionDto() {
    }
}
