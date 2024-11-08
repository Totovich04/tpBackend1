package utn.frc.bda.notificacion.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosicionDto {
    private Integer id;
    private VehiculoDto vehiculo;
    private Coordenadas coord;
    private String message;

    @JsonCreator
    public PosicionDto(
            @JsonProperty("id") int id,
            @JsonProperty("vehiculo") VehiculoDto vehiculo,
            @JsonProperty("coordenadas") Coordenadas coordenadas,
            @JsonProperty("mensaje") String mensaje) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.coord = coordenadas;
        this.message = mensaje;
    }


    @Data
    @NoArgsConstructor
    public static class Coordenadas {
        private double lat;
        private double lon;

        @JsonCreator
        public Coordenadas(
                @JsonProperty("lat") double lat,
                @JsonProperty("lon") double lon) {
            this.lat = lat;
            this.lon = lon;
        }
    }

}
