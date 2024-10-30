package utn.frc.bda.agencia.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class InteresadoDto {

    private Integer id;

    private String tipoDocumento;
    private String Documento;
    private String nombre;
    private String apellido;
    private Boolean restringido;
    private Integer nroLicencia;
    private Date fechaVencimientoLicencia;
}
