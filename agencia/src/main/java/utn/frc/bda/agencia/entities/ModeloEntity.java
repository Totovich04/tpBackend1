package utn.frc.bda.agencia.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Modelos")
@Data
public class ModeloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MARCA")
    private MarcaEntity marca;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "modelo", cascade = CascadeType.PERSIST)
    private Set<VehiculoEntity> vehiculoEntities = new HashSet<>();

    public void setMarca(MarcaEntity marca){
        this.marca = marca;
        if (marca!= null){
            marca.getModeloEntities().add(this);
        }
    }
}
