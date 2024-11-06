package utn.frc.bda.agencia.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class PosicionDto {
    private Integer id;
    private VehiculoDto idVehiculo;
    private Coordenadas coordenadas;
    private Date fechaHora;
    private String mensaje;

    @Data
    public static class Coordenadas{
        private Double lat;
        private Double lon;
    }
}
