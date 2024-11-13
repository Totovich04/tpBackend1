package utn.frc.bda.agencia.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.bda.agencia.entities.EmpleadoEntity;

@Data
@AllArgsConstructor
public class EmpleadoDto {
    private Integer legajo;
    private String nombre;
    private String apellido;
    private Integer telefonoContacto;

    public EmpleadoDto(EmpleadoEntity empleado){
            this.legajo = empleado.getLegajo();
            this.nombre = empleado.getNombre();
            this.apellido = empleado.getApellido();
            this.telefonoContacto = empleado.getTelefonoContacto();
    }
}
