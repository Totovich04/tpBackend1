package utn.frc.bda.agencia.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Pruebas")
@Data
public class PruebaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String comentarios = null;

    @Column(name = "FECHA_HORA_INICIO")
    private Date fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private Date fechaHoraFin = null;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    private VehiculoEntity vehiculo;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INTERESADO")
    private InteresadoEntity interesado;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPLEADO")
    private EmpleadoEntity empleado;

    public void setVehiculo(VehiculoEntity vehiculo) {
        this.vehiculo = vehiculo;
        if (vehiculo != null) {
            vehiculo.getPruebaEntities().add(this);

        }
    }

    public void setInteresado(InteresadoEntity interesado) {
        this.interesado = interesado;
        if (interesado != null) {
            interesado.getPruebaEntities().add(this);
        }
    }

    public void setEmpleado(EmpleadoEntity empleado){
        this.empleado = empleado;
        if (empleado != null) {
            empleado.getPruebaEntities().add(this);
        }
    }
}
