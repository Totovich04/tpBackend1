package utn.frc.bda.notificacion.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import utn.frc.bda.notificacion.dtos.PosicionDto;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "NOTIFICACION_ZONA_PELIGROSA")
public class NotificacionZonaPeligrosaEntity extends NotificacionEntity{
    private Double latitudActual;
    private Double longitudActual;
    private String nivelPeligro;
    private Integer idVehiculo;

    //Constructor
    public NotificacionZonaPeligrosaEntity(PosicionDto posicion, String nivelPeligro, Integer idVehiculo) {
        this.latitudActual = posicion.getCoord().getLat();
        this.longitudActual = posicion.getCoord().getLon();
        this.nivelPeligro = nivelPeligro;
        this.idVehiculo = idVehiculo;
    }

    public NotificacionZonaPeligrosaEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, PosicionDto posicion, String nivelPeligro, Integer idVehiculo) {
        super(id, fechaNotificacion, mensaje);
        this.latitudActual = posicion.getCoord().getLat();
        this.longitudActual = posicion.getCoord().getLon();
        this.nivelPeligro = nivelPeligro;
        this.idVehiculo = idVehiculo;
    }
}
