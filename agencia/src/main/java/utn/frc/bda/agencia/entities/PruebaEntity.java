package utn.frc.bda.agencia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
@Entity
@Data
@Table(name = "Pruebas")
public class PruebaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    private VehiculoEntity vehiculo;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPLEADO")
    private EmpleadoEntity empleado;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INTERESADO")
    private InteresadoEntity interesado;

    @Column(name = "FECHA_HORA_INICIO")
    private Date fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private Date fechaHoraFin = null;

    private String comentarios = null;

    public void setVehiculo(VehiculoEntity vehiculo) {
        this.vehiculo = vehiculo;
        if (vehiculo != null) {
            vehiculo.getPruebas().add(this);
        }
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
        if (empleado != null) {
            empleado.getPruebas().add(this);
        }
    }

    public void setInteresado(InteresadoEntity interesado) {
        this.interesado = interesado;
        if (interesado != null) {
            interesado.getPruebas().add(this);
        }
    }
}
