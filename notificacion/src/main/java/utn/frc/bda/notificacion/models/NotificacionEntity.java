package utn.frc.bda.notificacion.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class NotificacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime fechaNotificacion;
    private String mensaje;

    public NotificacionEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje){
        this.id = id;
        this.fechaNotificacion = fechaNotificacion;
        this.mensaje = mensaje;
    }
}
