package utn.frc.bda.notificacion.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Notificacion {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaNotificacion;
    private String texto;

    // Constructor
    public Notificacion(Integer id, LocalDateTime fechaNotificacion, String texto) {
        this.id = id;
        this.fechaNotificacion = fechaNotificacion;
        this.texto = texto;
    }
}