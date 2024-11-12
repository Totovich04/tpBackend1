package utn.frc.bda.agencia.dtos;

import lombok.Data;

@Data
public class PruebasConIncidentesDto {
    private PruebaDto prueba;
    private Integer totalIncidentes;

    public PruebasConIncidentesDto(PruebaDto prueba, Integer totalIncidentes) {
        this.prueba = prueba;
        this.totalIncidentes = totalIncidentes;
    }
}
