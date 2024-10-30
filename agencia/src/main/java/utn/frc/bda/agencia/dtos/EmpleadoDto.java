package utn.frc.bda.agencia.dtos;

import lombok.Data;

@Data
public class EmpleadoDto {
    private Integer legajo;
    private String nombre;
    private String apellido;
    private Integer telefonoContacto;
}
