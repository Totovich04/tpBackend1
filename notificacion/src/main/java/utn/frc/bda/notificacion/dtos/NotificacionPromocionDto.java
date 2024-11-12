package utn.frc.bda.notificacion.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.frc.bda.notificacion.models.NotificacionPromocion;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionPromocionDto extends NotificacionDto{
    private String codigo;
    private LocalDate fechaExpiracion;

    public NotificacionPromocionDto() {}

    public NotificacionPromocionDto(NotificacionPromocion notificacion) {
        super(notificacion.getId(), notificacion.getFechaNotificacion(), notificacion.getTexto());
        this.codigo = notificacion.getCodigoPromocion();
        this.fechaExpiracion = notificacion.getFechaExpiracion();
    }
}
