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
    private Integer IdModelo;
    private Integer anio;

    // Indica que este constructor será utilizado para crear instancias de la clase a partir de los valores de un JSON.
    @JsonCreator
    public VehiculoDto(
            @JsonProperty("id") Integer id,
            @JsonProperty("patente") String patente,
            @JsonProperty("IdModelo") Integer IdModelo,
            @JsonProperty("anio") Integer anio) {
        this.id = id;
        this.patente = patente;
        this.IdModelo = IdModelo;
        this.anio = anio;
    }
}