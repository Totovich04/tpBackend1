package utn.frc.bda.agencia.dtos.report;

import com.tpi.agencia.dtos.PruebaDto;
import lombok.Data;
import java.util.List;

@Data
public class PruebaResponse {
    private Integer totalPruebas;
    private List<PruebaDto> pruebas;

    public PruebasResponse(List<PruebaDto> pruebas) {
        this.pruebas = pruebas;
        this.totalPruebas = pruebas.size();
    }
}
