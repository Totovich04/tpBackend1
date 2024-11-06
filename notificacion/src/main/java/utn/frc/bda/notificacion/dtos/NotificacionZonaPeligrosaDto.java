package utn.frc.bda.notificacion.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.frc.bda.notificacion.models.NotificacionZonaPeligrosaEntity;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionZonaPeligrosaDto extends  NotificacionDto{
    private Double latActual;
    private Double lonActual;
    private String nivelPeligro;
    private Integer idVehiculo;

    public NotificacionZonaPeligrosaDto(NotificacionZonaPeligrosaEntity notificacion) {
        super(notificacion.getId(), notificacion.getFechaNotificacion(), notificacion.getMensaje());
        this.latActual = notificacion.getLatActual();
        this.lonActual = notificacion.getLonActual();
        this.nivelPeligro = notificacion.getNivelPeligro();
        this.idVehiculo = notificacion.getIdVehiculo();
    }
}
