package utn.frc.bda.notificacion.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionZonaPeligrosaDto extends  NotificacionDto{
    private String zona;
    private String nivelPeligro;
    private Integer idPrueba;

    public NotificacionZonaPeligrosaDto(NotificacionZonaPeligrosaDto notificacionZonaPeligrosaDto){
        super(notificacionZonaPeligrosaDto.getId(), notificacionZonaPeligrosaDto.getFechaNotificacion(), notificacionZonaPeligrosaDto.getMensaje());
        this.zona = notificacionZonaPeligrosaDto.getZona();
        this.nivelPeligro = notificacionZonaPeligrosaDto.getNivelPeligro();
        this.idPrueba = notificacionZonaPeligrosaDto.getIdPrueba();
    }
}
