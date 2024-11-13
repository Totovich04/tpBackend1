package utn.frc.bda.agencia.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class PosicionDto {
    private int id;
    private VehiculoDto vehiculo;
    private Coordenadas coordenadas;
//    private Date fechaHora;
    private String mensaje;

    @Data
    public static class Coordenadas{
        private Double lat;
        private Double lon;
    }
}
