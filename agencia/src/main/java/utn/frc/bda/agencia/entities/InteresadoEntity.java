package utn.frc.bda.agencia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Interesados")
public class InteresadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;

    private String documento;

    private String nombre;

    private String apellido;

    private Boolean restringido;

    @Column(name = "NRO_LICENCIA")
    private Integer nroLicencia;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private Date fechaVtoLicencia;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "interesado", cascade = CascadeType.PERSIST)
    private Set<PruebaEntity> pruebas = new HashSet<>();
}
