package utn.frc.bda.agencia.dtos.report;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.frc.bda.agencia.dtos.PruebaDto;
import utn.frc.bda.agencia.dtos.PruebasConIncidentesDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class IncidentesResponse extends ReportResponse {
    private Integer totalIncidentes;
    private List<PruebasConIncidentesDto> pruebasConIncidentes;

    public IncidentesResponse(List<PruebaDto> pruebasConIncidentes) {
        super("Reporte de incidentes", "Reporte de las pruebas que tienen accidentes registrados.");
        this.totalIncidentes = pruebasConIncidentes.size();

        Map<Integer, List<PruebaDto>> pruebasAgrupadas = pruebasConIncidentes.stream().collect(Collectors.groupingBy(PruebaDto::getId));

        this.pruebasConIncidentes = pruebasAgrupadas.entrySet().stream()
                .map(entry -> new PruebasConIncidentesDto(
                        entry.getValue().get(0),
                        entry.getValue().size()
                ))
                .collect(Collectors.toList());
    }
}
