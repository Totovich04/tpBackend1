package utn.frc.bda.agencia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter @NoArgsConstructor @ToString @AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table (name = "Empleados")
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer legajo;

    private String nombre;

    private String apellido;

    @Column(name = "telefono_contacto")
    private Integer telefonoContacto;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.PERSIST)
    private Set<PruebaEntity> pruebas = new HashSet<>();
}