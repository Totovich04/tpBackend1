package utn.frc.bda.notificacion.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.frc.bda.notificacion.models.NotificacionPromocionEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionPromocionDto extends NotificacionDto{
    private String codigoPromocion;
    private LocalDate fechaExpiracion;

    public NotificacionPromocionDto(NotificacionPromocionEntity notificacion) {
        super(notificacion.getId(), notificacion.getFechaNotificacion(), notificacion.getMensaje());
        this.codigoPromocion = notificacion.getCodigoPromocion();
        this.fechaExpiracion = notificacion.getFechaExpiracion();
    }
}
