package utn.frc.bda.agencia.dtos.report;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.frc.bda.agencia.dtos.PruebaDto;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PruebaResponse extends ReportResponse{
    private Integer totalPruebas;
    private List<PruebaDto> pruebaDtos;

    public PruebaResponse(String nombreReporte, String descripcionReporte, List<PruebaDto> pruebaDtos) {
        super(nombreReporte, descripcionReporte);
        this.pruebaDtos = pruebaDtos;
        this.totalPruebas = pruebaDtos.size();
    }
}
