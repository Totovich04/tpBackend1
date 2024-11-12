package utn.frc.bda.notificacion.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.frc.bda.notificacion.models.NotificacionRadio;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotificacionRadioDto extends NotificacionDto{
    private double latitud;
    private double longitud;
    private Integer idVehiculo;

    public NotificacionRadioDto(NotificacionRadio notificacionRadio){
        super(notificacionRadio.getId(), notificacionRadio.getFechaNotificacion(), notificacionRadio.getTexto());
        this.latitud = notificacionRadio.getLatitud();
        this.longitud = notificacionRadio.getLongitud();
        this.idVehiculo = notificacionRadio.getIdVehiculo();
    }
}
