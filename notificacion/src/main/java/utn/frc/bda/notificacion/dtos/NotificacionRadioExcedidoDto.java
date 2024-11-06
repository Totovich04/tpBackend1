package utn.frc.bda.notificacion.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import utn.frc.bda.notificacion.models.NotificacionRadioExcedidoEntity;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotificacionRadioExcedidoDto extends NotificacionDto {
    private Double latActual;
    private Double lonActual;
    private Integer idVehiculo;


    public NotificacionRadioExcedidoDto(NotificacionRadioExcedidoEntity notificacion) {
        super(notificacion.getId(), notificacion.getFechaNotificacion(), notificacion.getMensaje());
        this.latActual = notificacion.getLatitudActual();
        this.lonActual = notificacion.getLongitudActual();
        this.idVehiculo = notificacion.getIdVehiculo();
    }
}
