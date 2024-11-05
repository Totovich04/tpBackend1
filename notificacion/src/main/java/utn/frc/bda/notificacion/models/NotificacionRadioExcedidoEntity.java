package utn.frc.bda.notificacion.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.query.sql.spi.ParameterOccurrence;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionRadioExcedidoEntity extends NotificacionEntity {
    private Double latitudActual;
    private String longitudActual;
    private Integer idVehiculo;

    public NotificacionRadioExcedidoEntity(ParameterOccurrence pos, Integer idVehiculo){
        this.latitudActual = pos.getCoo
    }


    public NotificacionRadioExcedidoEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, Double latitudActual, String longitudActual, Integer idVehiculo) {
        super(id, fechaNotificacion, mensaje);
        this.latitudActual = latitudActual;
        this.longitudActual = longitudActual;
        this.idVehiculo = idVehiculo;
    }
}
