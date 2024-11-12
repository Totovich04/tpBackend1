package utn.frc.bda.agencia.dtos.report;

import com.tpi.agencia.dtos.VehiculoDto;
import com.tpi.agencia.models.VehiculoEntity;
import lombok.Data;

import java.util.Date;

@Data
public class DistanciaVehiculoResponse {
    private VehiculoDto vehiculo;
    private Date fechaDesde;
    private Date fechaHasta;
    private Double distanciaTotal;

    public DistanciaVehiculoResponse(VehiculoEntity vehiculo, Date fechaDesde, Date fechaHasta, Double distanciaTotal) {
        this.vehiculo = new VehiculoDto(vehiculo);
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.distanciaTotal = distanciaTotal;
    }


}
