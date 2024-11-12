package utn.frc.bda.notificacion.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.frc.bda.notificacion.models.NotificacionZona;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotificacionZonaDto extends NotificacionDto {
    private double latitud;
    private double longitud;
    private String nivelPeligro;
    private Integer idVehiculo;

    public NotificacionZonaDto(NotificacionZona notificacionZona){
        super(notificacionZona.getId(), notificacionZona.getFechaNotificacion(), notificacionZona.getTexto());
        this.latitud = notificacionZona.getLatitud();
        this.longitud = notificacionZona.getLongitud();
        this.nivelPeligro = notificacionZona.getNivelPeligro();
        this.idVehiculo = notificacionZona.getIdVehiculo();
    }
}
