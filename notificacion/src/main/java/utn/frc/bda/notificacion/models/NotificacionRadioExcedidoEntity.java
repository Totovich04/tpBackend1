package utn.frc.bda.notificacion.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

import utn.frc.bda.notificacion.dtos.PosicionDto;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
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
