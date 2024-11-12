package utn.frc.bda.agencia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
@Entity
@Table(name = "Posiciones")
public class PosicionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    private VehiculoEntity vehiculo;

    @Column(name = "FECHA_HORA")
    private Date fechaHora;

    private Double latitud;

    private Double longitud;

    public void setVehiculo(VehiculoEntity vehiculo) {
        this.vehiculo = vehiculo;
        if (vehiculo != null) {
            vehiculo.getPosiciones().add(this);
        }
    }
}
