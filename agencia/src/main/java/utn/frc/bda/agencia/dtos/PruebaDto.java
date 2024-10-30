package utn.frc.bda.agencia.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class PruebaDto {

    private Integer id;
    private Integer idVehiculo;
    private Integer idInteresado;
    private Integer idEmpleado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private String comentarios;
}
