package utn.frc.bda.notificacion.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehiculoDto {
    private Integer id;
    private String patente;
    private Integer idModelo;
    private Integer anio;

    @JsonCreator
    public VehiculoDto(
            @JsonProperty("id") Integer id,
            @JsonProperty("patente") String patente,
            @JsonProperty("idModelo") Integer idModelo,
            @JsonProperty("anio") Integer anio) {
        this.id = id;
        this.patente = patente;
        this.idModelo = idModelo;
        this.anio = anio;
    }
}
