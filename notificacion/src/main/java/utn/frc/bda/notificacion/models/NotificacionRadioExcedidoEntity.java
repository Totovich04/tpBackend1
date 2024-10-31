package utn.frc.bda.notificacion.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionRadioExcedidoEntity extends NotificacionEntity {
    private Double radioMaximo;
    private String ubicacionActual;
    private Integer idPrueba;

    public NotificacionRadioExcedidoEntity(Double radioMaximo, String ubicacionActual, Integer idPrueba) {
        this.radioMaximo = radioMaximo;
        this.ubicacionActual = ubicacionActual;
        this.idPrueba = idPrueba;
    }

    public NotificacionRadioExcedidoEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, Double radioMaximo, String ubicacionActual, Integer idPrueba) {
        super(id, fechaNotificacion, mensaje);
        this.radioMaximo = radioMaximo;
        this.ubicacionActual = ubicacionActual;
        this.idPrueba = idPrueba;
    }
}
