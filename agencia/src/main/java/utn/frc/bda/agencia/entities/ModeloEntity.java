package utn.frc.bda.agencia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
@Entity
@Table(name = "Modelos")
public class ModeloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MARCA")
    @JsonIgnore
    private MarcaEntity marca;

    private String descripcion;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "modelo", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<VehiculoEntity> vehiculos;

    public void setMarca(MarcaEntity marca) {
        this.marca = marca;
        if (marca != null) {
            marca.getModelos().add(this);
        }
    }
}
