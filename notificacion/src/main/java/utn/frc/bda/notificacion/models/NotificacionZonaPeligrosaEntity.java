package utn.frc.bda.notificacion.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionZonaPeligrosaEntity extends NotificacionEntity{
    private String zona;
    private String nivelPeligro;
    private Integer idPrueba;

    public NotificacionZonaPeligrosaEntity(String zona, String nivelPeligro, Integer idPrueba) {
        this.zona = zona;
        this.nivelPeligro = nivelPeligro;
        this.idPrueba = idPrueba;
    }

    public NotificacionZonaPeligrosaEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, String zona, String nivelPeligro, Integer idPrueba) {
        super(id, fechaNotificacion, mensaje);
        this.zona = zona;
        this.nivelPeligro = nivelPeligro;
        this.idPrueba = idPrueba;
    }
}
