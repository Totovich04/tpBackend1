package utn.frc.bda.agencia.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.bda.agencia.entities.VehiculoEntity;

@Data
@AllArgsConstructor
public class VehiculoDto {
    private Integer id;
    private String patente;
    private Integer idModelo;
    private Integer anio;

    public VehiculoDto(VehiculoEntity vehiculo) {
        this.id = vehiculo.getId();
        this.patente = vehiculo.getPatente();
        this.idModelo = vehiculo.getModelo().getId();
        this.anio = vehiculo.getAnio();
    }
}
