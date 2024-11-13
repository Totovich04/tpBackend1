package utn.frc.bda.agencia.dtos.report;

import lombok.Data;
import utn.frc.bda.agencia.dtos.PruebaDto;

import java.util.List;

@Data
public class PruebaResponse extends ReportResponse{
    private Integer totalPruebas;
    private List<PruebaDto> pruebas;

    public PruebaResponse(List<PruebaDto> pruebas) {
        this.pruebas = pruebas;
        this.totalPruebas = pruebas.size();
    }
}
