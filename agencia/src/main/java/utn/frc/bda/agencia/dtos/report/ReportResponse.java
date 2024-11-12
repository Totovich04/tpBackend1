package utn.frc.bda.agencia.dtos.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public abstract class ReportResponse {
    private String nombreReporte;
    private String descripcionReporte;
    private Date fechaCreacion;

    public ReportResponse(){
        this.fechaCreacion = new Date();
    }
    public ReportResponse(String nombreReporte, String descripcionReporte) {
        this.nombreReporte = nombreReporte;
        this.descripcionReporte = descripcionReporte;
        this.fechaCreacion = new Date();
    }
}
