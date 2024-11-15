package utn.frc.bda.notificacion.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.frc.bda.notificacion.models.NotificacionPromocion;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
// La clase NotificacionPromocionDto extiende de NotificacionDto, lo que significa que hereda sus atributos y métodos.
public class NotificacionPromocionDto extends NotificacionDto{
    private String codigo;
    private LocalDate fechaExpiracion;

    // Constructor sin parámetros, se utiliza para crear una instancia vacía del objeto NotificacionPromocionDto.
    public NotificacionPromocionDto() {}

    // Constructor que recibe un objeto NotificacionPromocion para inicializar los valores del DTO.
    public NotificacionPromocionDto(NotificacionPromocion notificacion) {
        super(notificacion.getId(), notificacion.getFechaNotificacion(), notificacion.getTexto());
        this.codigo = notificacion.getCodigoPromocion();
        this.fechaExpiracion = notificacion.getFechaExpiracion();
    }
}
