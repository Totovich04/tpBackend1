package utn.frc.bda.agencia.dtos;


import lombok.Data;

@Data
public class VehiculoDto {
    private Integer id;
    private String patente;
    private Integer idModelo;
    private Integer anio;
}
