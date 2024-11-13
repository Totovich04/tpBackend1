package utn.frc.bda.agencia.dtos;

import lombok.Data;

@Data
public class PruebasConIncidentesDto {
    private PruebaDto prueba;
    private Integer cantidadIncidentes;

    public PruebasConIncidentesDto(PruebaDto prueba, Integer cantidadIncidentes) {
        this.prueba = prueba;
        this.cantidadIncidentes = cantidadIncidentes;
    }
}
