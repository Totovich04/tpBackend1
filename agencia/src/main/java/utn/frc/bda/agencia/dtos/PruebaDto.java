package utn.frc.bda.agencia.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.bda.agencia.entities.PruebaEntity;

import java.util.Date;

@Data
@AllArgsConstructor
public class PruebaDto {

    private Integer id;
    private VehiculoDto idVehiculo;
    private InteresadoDto idInteresado;
    private EmpleadoDto idEmpleado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private String comentarios;

    public PruebaDto(PruebaEntity prueba) {
        this.idVehiculo = new VehiculoDto(prueba.getVehiculo());
        this.idEmpleado = new EmpleadoDto(prueba.getEmpleado());
        this.idInteresado = new InteresadoDto(prueba.getInteresado());
        this.fechaHoraInicio = prueba.getFechaHoraInicio();
        this.fechaHoraFin = prueba.getFechaHoraFin();
        this.comentarios = prueba.getComentarios();
    }
}
