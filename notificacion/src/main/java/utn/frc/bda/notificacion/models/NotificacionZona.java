package utn.frc.bda.notificacion.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import utn.frc.bda.notificacion.dtos.PosicionDto;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor

@Entity
@Table(name = "NOTIFICACION_ZONA")
public class NotificacionZona extends Notificacion{
    private double latitud;
    private double longitud;
    private String nivelPeligro;
    private Integer idVehiculo;

    //Constructor
    public NotificacionZona(PosicionDto posicion, String nivelPeligro, Integer idVehiculo) {
        this.latitud = posicion.getCoordenadas().getLatitud();
        this.longitud = posicion.getCoordenadas().getLongitud();
        this.nivelPeligro = nivelPeligro;
        this.idVehiculo = idVehiculo;
    }

    //Constructor completo
    public NotificacionZona(Integer id, LocalDateTime fechaNotificacion, String texto, PosicionDto posicion, String nivelPeligro, Integer idVehiculo) {
        super(id, fechaNotificacion, texto);
        this.latitud = posicion.getCoordenadas().getLatitud();
        this.longitud = posicion.getCoordenadas().getLongitud();
        this.nivelPeligro = nivelPeligro;
        this.idVehiculo = idVehiculo;
    }

}