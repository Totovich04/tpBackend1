package utn.frc.bda.notificacion.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NotificacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaNotificacion;
    private String mensaje;

    public NotificacionEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje){
        this.id = id;
        this.fechaNotificacion = fechaNotificacion;
        this.mensaje = mensaje;
    }
}
