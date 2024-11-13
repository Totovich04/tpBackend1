package utn.frc.bda.agencia.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.bda.agencia.entities.InteresadoEntity;

import java.util.Date;

@Data
@AllArgsConstructor
public class InteresadoDto {

    private Integer id;
    private String tipoDocumento;
    private String documento;
    private String nombre;
    private String apellido;
    private Boolean restringido;
    private Integer nroLicencia;
    private Date fechaVtoLicencia;

    public InteresadoDto(InteresadoEntity interesado) {
        this.id = interesado.getId();
        this.tipoDocumento = interesado.getTipoDocumento();
        this.documento = interesado.getDocumento();
        this.nombre = interesado.getNombre();
        this.apellido = interesado.getApellido();
        this.restringido = interesado.getRestringido();
        this.nroLicencia = interesado.getNroLicencia();
        this.fechaVtoLicencia = interesado.getFechaVtoLicencia();
    }
}
