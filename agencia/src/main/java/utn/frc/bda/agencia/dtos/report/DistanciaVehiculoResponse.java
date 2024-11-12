package utn.frc.bda.agencia.dtos.report;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.frc.bda.agencia.dtos.VehiculoDto;
import utn.frc.bda.agencia.entities.VehiculoEntity;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class DistanciaVehiculoResponse extends ReportResponse{
    private VehiculoDto vehiculo;
    private Date fechaDesde;
    private Date fechaHasta;
    private Double distanciaRecorrida;

    public DistanciaVehiculoResponse(VehiculoEntity vehiculo, Date fechaDesde, Date fechaHasta, Double distanciaRecorrida) {
        super("Distancia recorrida por vehiculo", "Distancia recorrida por vehiculo");
        this.vehiculo = new VehiculoDto(vehiculo);
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.distanciaRecorrida = distanciaRecorrida;
    }
}
