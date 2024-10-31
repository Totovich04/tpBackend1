package utn.frc.bda.agencia.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Vehiculos")
@Data
public class VehiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String patente;
    private Integer anio;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MODELO")
    private ModeloEntity modelo;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.PERSIST)
    private Set<PruebaEntity> pruebaEntities = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.PERSIST)
    private Set<PosicionEntity> posicionEntities;

    public void setModelo(ModeloEntity modelo) {
        this.modelo = modelo;
        if (modelo != null) {
            modelo.getVehiculoEntities().add(this);
        }
    }
}
