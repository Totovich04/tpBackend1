package utn.frc.bda.notificacion.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Table(name = "NOTIFICACION_PROMOCION")
public class NotificacionPromocionEntity extends NotificacionEntity {

    private String codigoPromocion;
    private LocalDate fechaExpiracion;

    public NotificacionPromocionEntity(String codigoPromocion, LocalDate fechaExpiracion) {
        this.codigoPromocion = codigoPromocion;
        this.fechaExpiracion = fechaExpiracion;
    }

    public NotificacionPromocionEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, String codigoPromocion, LocalDate fechaExpiracion){
        super(id, fechaNotificacion, mensaje);
        this.codigoPromocion = codigoPromocion;
        this.fechaExpiracion = fechaExpiracion;
    }

}
