package utn.frc.bda.notificacion.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionPromocionEntity extends NotificacionEntity {

    private String codigoPromocion;
    private LocalDate fechaExpiracion;

    public NotificacionPromocionEntity(String codigoPromocion, LocalDate fechaExpiracion) {
        this.codigoPromocion = codigoPromocion;
        this.fechaExpiracion = fechaExpiracion;
    }

    public NotificacionPromocionEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, String codigoPromocion, LocalDate fechaExpiracion){
        super(id, fechaNotificacion, mensaje);
        this.codigoPromocion = codigoPromocion;
        this.fechaExpiracion = fechaExpiracion;
    }
}
