package utn.frc.bda.agencia.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class PosicionDto {
    private Integer id;
    private Integer idVehiculo;
    private Date fechaHora;
    private Double latitud;
    private Double longitud;
}
