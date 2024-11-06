package utn.frc.bda.agencia.dtos.externos;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class NotificacionZonaPeligrosaDto extends NotificacionDto{
    private double latActual;
    private double lngActual;
    private String nivelPeligro;
    private Integer idVehiculo;
}
