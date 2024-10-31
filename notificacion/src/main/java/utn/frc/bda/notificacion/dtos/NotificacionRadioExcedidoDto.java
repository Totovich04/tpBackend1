package utn.frc.bda.notificacion.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.frc.bda.notificacion.models.NotificacionRadioExcedidoEntity;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionRadioExcedidoDto extends NotificacionDto{
    private Double radioMaximo;
    private String ubicacionActual;
    private Integer idPrueba;

    public NotificacionRadioExcedidoDto(NotificacionRadioExcedidoEntity notificacion) {
        super(notificacion.getId(), notificacion.getFechaNotificacion(), notificacion.getMensaje());
        this.radioMaximo = notificacion.getRadioMaximo();
        this.ubicacionActual = notificacion.getUbicacionActual();
        this.idPrueba = notificacion.getIdPrueba();
    }
}
