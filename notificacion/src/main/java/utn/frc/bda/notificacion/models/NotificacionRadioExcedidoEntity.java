package utn.frc.bda.notificacion.models;

import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import utn.frc.bda.notificacion.dtos.PosicionDto;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "NOTIFICACION_RADIO_EXCEDIDO")
public class NotificacionRadioExcedidoEntity extends NotificacionEntity {
    private Double latitudActual;
    private Double longitudActual;
    private Integer idVehiculo;

    public NotificacionRadioExcedidoEntity(PosicionDto pos, Integer idVehiculo){
        this.latitudActual = pos.getCoord().getLat();
        this.longitudActual = pos.getCoord().getLon();
        this.idVehiculo = idVehiculo;
    }

    public NotificacionRadioExcedidoEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, PosicionDto pos, Integer idVehiculo) {
        super(id, fechaNotificacion, mensaje);
        this.latitudActual = pos.getCoord().getLat();
        this.longitudActual = pos.getCoord().getLon();
        this.idVehiculo = idVehiculo;
    }

}
